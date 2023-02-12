package tn.esprit.spring.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.SecuritiesAccount;
import tn.esprit.spring.repository.SecuritiesAccountRepo;
import tn.esprit.spring.service.Interface.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/saccount")
public class SecuritiesAccountController {
@Autowired
SecuritiesAccountRepo SaccountRepo;
@Autowired
IUserService userService;
@PostMapping("/addsaccount")
public String addSaccount(@RequestBody SecuritiesAccount saccount)
{
	SaccountRepo.save(saccount);
	return "SecuritiesAccount added!";
}
@GetMapping("/getsAccount/{username}")
public Set<SecuritiesAccount> GetSaccount(@PathVariable String username)
{
	return userService.GetSaccount(username);
	
}
}
