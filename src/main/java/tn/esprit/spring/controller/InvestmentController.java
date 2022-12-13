package tn.esprit.spring.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Investment;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.exceptions.ApiException;
import tn.esprit.spring.repository.SecuritiesAccountRepo;
import tn.esprit.spring.repository.StockDataRepo;
import tn.esprit.spring.service.Interface.ISecuritiesAccountService;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {
@Autowired
StockDataRepo stockDataRepo;
@Autowired
SecuritiesAccountRepo securitiesAccountRepo;
@Autowired
ISecuritiesAccountService SaccountSer;

@GetMapping("/refreshAccount")
private void updateAccountInvestments(@RequestParam Long sAccountId) {
	SecuritiesAccount saccount = securitiesAccountRepo.findById(sAccountId).get();
	saccount.getInvestments().forEach(i -> {
        try {
            this.stockDataRepo.updateStock(i.getStock());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    });

	SaccountSer.refresh(sAccountId);
	securitiesAccountRepo.save(saccount);
}

private Optional<Investment> getInvestmentFromId(SecuritiesAccount saccount, Integer investmentId) {
    Investment investment = null;

    List<Investment> userInvestments = saccount.getInvestments();

    System.out.println("User: " + saccount + " -> Updating investments: " + userInvestments);


    for (Investment userInvestment : userInvestments) {
        if (userInvestment.getId().intValue() == investmentId.intValue()) {
            investment = userInvestment;
            break;
        }
    }

    return Optional.ofNullable(investment);
}

@GetMapping("/investments")
public List<Investment> getUserInvestments(@RequestParam Long sAccountId) {
	SecuritiesAccount saccount = securitiesAccountRepo.findById(sAccountId).get();
    updateAccountInvestments(sAccountId);
    return saccount.getInvestments();
}

@PostMapping("/sell")
public ResponseEntity<String> sellStock(@RequestParam Long sAccountId, @RequestBody Map<String, String> payload) {

    String tempInvestmentId = payload.getOrDefault("investmentId","null");
    String stockSymbol = payload.getOrDefault("stockSymbol","null");
  
    	
    
    if (tempInvestmentId == null && stockSymbol == null) {
        throw new IllegalArgumentException("No investment or stockSymbol is provided");
    }
    
	SecuritiesAccount saccount = securitiesAccountRepo.findById(sAccountId).get();
    this.updateAccountInvestments(sAccountId);
    
    
    Optional<Investment> optionalInvestment = tempInvestmentId == null ? SaccountSer.getInvestmentFromStock(sAccountId, stockSymbol) : SaccountSer.getInvestmentFromId(sAccountId, Integer.parseInt(tempInvestmentId));

    if (optionalInvestment.isPresent()) {

        Investment investment = optionalInvestment.get();
       
        
        
        int quantity = Integer.parseInt(payload.getOrDefault("quantity", investment.getQuantity().toString()));

        
        Double sellingPrice = Double.parseDouble(payload.getOrDefault("sellingPrice", investment.getStock().getPrice().toString()));

        
        
        SaccountSer.sell(sAccountId,investment, quantity, sellingPrice);

    } else
        return ResponseEntity.badRequest().body("Invalid investment Id or stock Symbol ");

    securitiesAccountRepo.saveAndFlush(saccount);

    return ResponseEntity.ok("Stock sold successfully");

}


@PostMapping("/buy")
public ResponseEntity<String> buyStock(@RequestParam Long sAccountId, @RequestBody Map<String, String> payload) {

    String tempInvestmentId = payload.getOrDefault("investmentId", null);

	SecuritiesAccount saccount = securitiesAccountRepo.findById(sAccountId).get();
    this.updateAccountInvestments(sAccountId);

    Stock stock;
    int quantity;
    double buyPrice;

    if (tempInvestmentId != null) {

        int investmentId = Integer.parseInt(tempInvestmentId);

        Optional<Investment> optionalInvestment = this.getInvestmentFromId(saccount, investmentId);

        if (!optionalInvestment.isPresent())
            return ResponseEntity.badRequest().body("Invalid investment Id");

        Investment investment = optionalInvestment.get();

        stock = investment.getStock();

        quantity = Integer.parseInt(payload.getOrDefault("quantity", investment.getQuantity().toString()));


    } else {
        String stockSymbol = payload.getOrDefault("stockSymbol", null);

        if (stockSymbol == null)
            return ResponseEntity.badRequest().body("stock symbol required");

        try {
            stock = this.stockDataRepo.getStockFromSymbol(stockSymbol);
            quantity = Integer.parseInt(payload.getOrDefault("quantity", "0"));

            System.out.println("Successfully set: " + stock + " with q: " + quantity);

        } catch (ApiException e) {
            return ResponseEntity.badRequest().body("stock symbol/ investment Id required");
        }
    }

    buyPrice = Double.parseDouble(payload.getOrDefault("buyPrice", stock.getPrice().toString()));

    
    SaccountSer.buy(sAccountId, stock, quantity, buyPrice);
    securitiesAccountRepo.saveAndFlush(saccount);

    return ResponseEntity.ok("Stock purchased successfully");
}


}
