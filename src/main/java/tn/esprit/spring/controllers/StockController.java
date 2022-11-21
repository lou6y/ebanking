package tn.esprit.spring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.exceptions.ApiException;

import tn.esprit.spring.dao.entities.Stock;
import tn.esprit.spring.model.StockSearchResult;
import tn.esprit.spring.dao.repositories.StockDataRepo;
@RestController
@RequestMapping("/api/stock")
public class StockController {
	 // Used to fetch Data from the API and then convert it to a preferred format
    StockDataRepo stockDataRepo;

    @Autowired
    public StockController(StockDataRepo stockDataRepo) {
        this.stockDataRepo = stockDataRepo;
    }

    @GetMapping("/search")
    public ResponseEntity<List<StockSearchResult>> searchForStock(@RequestParam String stockName) throws ApiException {

        System.out.println("Looking for: " + stockName);
        return new ResponseEntity<>(this.stockDataRepo.searchForSymbol(stockName), HttpStatus.OK);
    }

    @GetMapping("/stock")
    public ResponseEntity<?> getStock(@RequestParam String symbol) throws ApiException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        try {
            Stock stock = this.stockDataRepo.getStockFromSymbol(symbol);
            return new ResponseEntity<>(stock, httpHeaders, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            System.out.println("Caught an illegal argument exception");
            return ResponseEntity.badRequest().headers(httpHeaders).body("No stock found for the symbol");
        }
    }
}
