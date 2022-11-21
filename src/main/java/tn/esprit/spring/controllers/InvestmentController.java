package tn.esprit.spring.controllers;


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

import tn.esprit.spring.dao.entities.Investment;
import tn.esprit.spring.dao.entities.SecuritiesAccount;
import tn.esprit.spring.dao.entities.Stock;
import tn.esprit.spring.dao.repositories.SecuritiesAccountRepo;
import tn.esprit.spring.dao.repositories.StockDataRepo;
import tn.esprit.spring.exceptions.ApiException;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {
@Autowired
StockDataRepo stockDataRepo;
@Autowired
SecuritiesAccountRepo securitiesAccountRepo;

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

    saccount.refresh();
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
    this.updateAccountInvestments(sAccountId);
    return saccount.getInvestments();
}

@PostMapping("/sell")
public ResponseEntity<String> sellStock(@RequestParam Long sAccountId, @RequestBody Map<String, String> payload) {

    String tempInvestmentId = payload.getOrDefault("investmentId", null);
    String stockSymbol = payload.getOrDefault("stockSymbol", null);

    if (tempInvestmentId == null && stockSymbol == null) {
        throw new IllegalArgumentException("No investment or stockSymbol is provided");
    }
    
	SecuritiesAccount saccount = securitiesAccountRepo.findById(sAccountId).get();
    this.updateAccountInvestments(sAccountId);

    Optional<Investment> optionalInvestment = tempInvestmentId == null ? saccount.getInvestmentFromStock(stockSymbol) : saccount.getInvestmentFromId(Integer.parseInt(tempInvestmentId));

    if (optionalInvestment.isPresent()) {

        Investment investment = optionalInvestment.get();

        int quantity = Integer.parseInt(payload.getOrDefault("quantity", investment.getQuantity().toString()));

        Double sellingPrice = Double.parseDouble(payload.getOrDefault("sellingPrice", investment.getStock().getLTP().toString()));

        saccount.sell(investment, quantity, sellingPrice);

    } else
        return ResponseEntity.badRequest().body("Invalid investment Id or stock Symbol ");

    securitiesAccountRepo.saveAndFlush(saccount);

    return ResponseEntity.ok("Stock purchased successfully");

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

    buyPrice = Double.parseDouble(payload.getOrDefault("buyPrice", stock.getLTP().toString()));

    saccount.buy(stock, quantity, buyPrice);

    securitiesAccountRepo.saveAndFlush(saccount);

    return ResponseEntity.ok("Stock purchased successfully");
}


}
