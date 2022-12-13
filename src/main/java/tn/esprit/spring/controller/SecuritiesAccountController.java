package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.repository.SecuritiesAccountRepo;

@RestController
@RequestMapping("/api/saccount")
public class SecuritiesAccountController {
@Autowired
SecuritiesAccountRepo SaccountRepo;
@PostMapping("/addsaccount")
public String addSaccount(@RequestBody SecuritiesAccount saccount)
{
	SaccountRepo.save(saccount);
	return "SecuritiesAccount added!";
}
}
