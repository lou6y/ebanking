package tn.esprit.spring.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Investment;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.InvestmentRepo;
import tn.esprit.spring.service.Interface.IInvestmentService;
@Service
public class InvestmentServiceImpl implements IInvestmentService{
@Autowired
InvestmentRepo investRepo;

// Supplying just the Stock, the Quantity and the buy Price
public Investment addInvestment(Stock stock, SecuritiesAccount securitiesAccount)
{
	Investment invest = new Investment();
    invest.setSecuritiesAccount(securitiesAccount);
    invest.setStock(stock);
    invest.setQuantity(0);
    invest.setNetInvested(0.0);
    invest.setCurrentValue(0.0);
    invest.setNetProfit(0.0);
    invest.setNetProfitPercentage(0.0);
    invest.setAverageBuyPrice(0.0);
    return investRepo.save(invest);
}
@Override
public Investment Invest(Stock stock, Integer quantity, Double averageBuyPrice, SecuritiesAccount securitiesAccount) {
	Investment invest = addInvestment(stock, securitiesAccount);
    buy(invest.getId(),quantity, averageBuyPrice);
    refresh(invest.getId());
    return investRepo.save(invest);
}


// Updates stock data based on latest LTP.
@Override
public void refresh(Integer idInvest) {
	Investment invest = investRepo.findById(idInvest).get();
	invest.setCurrentValue(invest.getStock().getPrice() * invest.getQuantity());
    invest.setNetProfit(invest.getNetInvested() - invest.getCurrentValue());
    invest.setNetProfitPercentage(invest.getNetProfit()/invest.getNetInvested() * 100);
    investRepo.save(invest);
}

//Sells the stock and returns the income from the sale
@Override
public Double sell(Integer idInvest, int quantity, Double sellingPrice) {
    Investment invest = investRepo.findById(idInvest).get();
    invest.setQuantity(invest.getQuantity()- quantity);
    invest.setNetInvested(invest.getQuantity()-invest.getQuantity()*invest.getAverageBuyPrice());
    refresh(idInvest);
    investRepo.save(invest);
    return (quantity * sellingPrice);
}

// Selling the stock at Market price and returning the income from the sale
@Override
public Double sell(Integer idInvest,int quantity) {
	Investment invest = investRepo.findById(idInvest).get();
    return sell(idInvest,quantity, invest.getStock().getPrice());   
}

// Selling all the stock at Market Price. and returning the income from the sale
@Override
public Double sell(Integer idInvest) {
	Investment invest = investRepo.findById(idInvest).get();
    return sell(idInvest,invest.getQuantity(), invest.getStock().getPrice());
}

//Buy a certain quantity of the stock at the specified price.
@Override
public void buy(Integer idInvest, int quantity, Double buyPrice) {
	Investment invest = investRepo.findById(idInvest).get();
	invest.setQuantity(invest.getQuantity()+quantity);
	invest.setNetInvested(invest.getNetInvested()+quantity * buyPrice);
    invest.setAverageBuyPrice(invest.getNetInvested()/invest.getQuantity());
    refresh(idInvest);
    investRepo.save(invest);   
}

// Buy a certain quantity of the stock at the Market Price.
@Override
public void buy(Integer idInvest, int quantity) {
	Investment invest = investRepo.findById(idInvest).get();
    buy(idInvest,quantity, invest.getStock().getPrice());
}
}
