package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.repositories.SecuritiesAccountRepo;

@RestController
public class SecuritiesAccountController {
@Autowired
SecuritiesAccountRepo securitiesAccountRepo;

}
