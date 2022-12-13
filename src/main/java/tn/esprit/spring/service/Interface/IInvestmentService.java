package tn.esprit.spring.service.Interface;

import tn.esprit.spring.entity.Investment;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.Stock;

public interface IInvestmentService {

	void refresh(Integer idInvest);

	Double sell(Integer idInvest, int quantity, Double sellingPrice);

	Double sell(Integer idInvest, int quantity);

	Double sell(Integer idInvest);

	void buy(Integer idInvest, int quantity, Double buyPrice);

	void buy(Integer idInvest, int quantity);

	Investment Invest(Stock stock, Integer quantity, Double averageBuyPrice, SecuritiesAccount securitiesAccount);

}
