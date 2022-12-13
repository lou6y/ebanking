package tn.esprit.spring.service.Implementation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Investment;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.InvestmentRepo;
import tn.esprit.spring.repository.SecuritiesAccountRepo;
import tn.esprit.spring.service.Interface.IInvestmentService;
import tn.esprit.spring.service.Interface.ISecuritiesAccountService;

@Service
public class SecuritiesAccountServiceImpl implements ISecuritiesAccountService{
	
	@Autowired
	SecuritiesAccountRepo SaccountRepo;
	
	@Autowired
	InvestmentRepo InvestRepo;
	
	@Autowired
    IInvestmentService InvestmentService;
	
	@Override
	public void refresh(Long sAccountId) {
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
	    AtomicReference<Double> tempUnrealizedGains = new AtomicReference<>(0.0);
	    AtomicReference<Double> tempPortfolioValue = new AtomicReference<>(0.0);
	    
	    List<Investment> investments = saccount.getInvestments();
	    
	    investments.forEach(i ->
	    {
	        tempUnrealizedGains.updateAndGet(v -> v + i.getNetProfit());
	        tempPortfolioValue.updateAndGet(v -> v + i.getCurrentValue());
	    });

	    saccount.setUnrealizedGains(tempUnrealizedGains.get());
	    saccount.setNetPortfolioValue(tempPortfolioValue.get());
	    
	    SaccountRepo.save(saccount);
	}
	
	@Override
	public void invest(Long sAccountId, Investment invest) {
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
        System.out.println(saccount);
		invest.setSecuritiesAccount(saccount);
		saccount.setNetInvested(saccount.getNetInvested() + invest.getNetInvested());
		saccount.setNetPortfolioValue(saccount.getNetPortfolioValue() + invest.getNetInvested());
        invest.setSecuritiesAccount(saccount);
        InvestRepo.save(invest);
	    refresh(sAccountId);
        SaccountRepo.save(saccount);
	}
	
	@Override
	public void buy(Long sAccountId, Stock stock, Integer quantity, Double buyPrice) {
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();

		
	    System.out.println(this + " buying " + quantity + " of " + stock + " at " + buyPrice);

	    Investment previousInvestment = null;

	    List<Investment> investments = saccount.getInvestments();
	    
	    for (Investment investment : investments)
	        if (investment.getStock().equals(stock)) {
	            previousInvestment = investment;
	            break;
	        }

	    if (previousInvestment != null)
	    	InvestmentService.buy(previousInvestment.getId(), quantity, buyPrice);
	    else
	        invest(sAccountId,InvestmentService.Invest(stock, quantity, buyPrice, saccount));

	    refresh(sAccountId);

	    System.out.println("After buying: investments: " + investments);
	    
	    SaccountRepo.save(saccount);

	}
	
	@Override
	public void sell(Long sAccountId, int investmentId, Integer quantity, Double sellingPrice) throws IllegalArgumentException {
	
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
	    Investment ownedInvestment = null;
	    List<Investment> investments = saccount.getInvestments();

	    for (Investment investment : investments)
	        if (investment.getId().equals(investmentId)) {
	            ownedInvestment = investment;
	            break;
	        }

	    if (ownedInvestment == null)
	        throw new IllegalArgumentException("You don't own this stock");

	    if (quantity > ownedInvestment.getQuantity())
	        throw new IllegalArgumentException("You don't own enough quantity of this stock");

	    sell(sAccountId, ownedInvestment, quantity, sellingPrice);
	    

	}

	@Override
	public void sell(Long sAccountId, Investment invest, Integer quantity, Double sellingPrice) {
		
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
		saccount.setNetInvested(saccount.getNetInvested() - invest.getNetInvested());
		saccount.setRealizedGains(saccount.getRealizedGains() + InvestmentService.sell(invest.getId(),quantity, sellingPrice) - invest.getNetInvested());

	    if (invest.getQuantity() == 0)
        invest.setSecuritiesAccount(null);
	    InvestRepo.save(invest);
	    refresh(sAccountId);
	   
	}
	
	@Override
	public Optional<Investment> getInvestmentFromStock(Long sAccountId, String stockSymbol) {
		
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
	    List<Investment> investments = saccount.getInvestments();

	    for (Investment i : investments) {
	        if (i.getStock().getSymbol().equals(stockSymbol))
	            return Optional.of(i);
	    }

	    return Optional.empty();

	}

	@Override
	public Optional<Investment> getInvestmentFromId(Long sAccountId, int investmentId) {
		
		SecuritiesAccount saccount = SaccountRepo.findById(sAccountId).get();
	    List<Investment> investments = saccount.getInvestments();
		
	    for (Investment i : investments) {
	        if (i.getId() == investmentId)
	            return Optional.of(i);
	    }

	    return Optional.empty();

	}
}
