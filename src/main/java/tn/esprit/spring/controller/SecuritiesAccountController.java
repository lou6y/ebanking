package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.repository.SecuritiesAccountRepo;

@RestController
public class SecuritiesAccountController {
@Autowired
SecuritiesAccountRepo securitiesAccountRepo;

}
