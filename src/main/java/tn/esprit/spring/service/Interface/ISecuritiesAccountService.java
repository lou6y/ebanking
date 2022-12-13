package tn.esprit.spring.service.Interface;

import java.util.Optional;

import tn.esprit.spring.entity.Investment;
import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.entity.Stock;

public interface ISecuritiesAccountService {

	void refresh(Long sAccountId);

	void invest(Long sAccountId, Investment invest);

	void buy(Long sAccountId, Stock stock, Integer quantity, Double buyPrice);

	void sell(Long sAccountId, int investmentId, Integer quantity, Double sellingPrice)
			throws IllegalArgumentException;

	void sell(Long sAccountId, Investment invest, Integer quantity, Double sellingPrice);

	Optional<Investment> getInvestmentFromStock(Long sAccountId, String stockSymbol);

	Optional<Investment> getInvestmentFromId(Long sAccountId, int investmentId);

}
