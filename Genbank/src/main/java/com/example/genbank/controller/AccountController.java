package com.example.genbank.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.genbank.entity.Account;
import com.example.genbank.entity.Transaction;
import com.example.genbank.service.AccountService;

@RestController		
@CrossOrigin(origins = "http://localhost:4200/")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("addaccount")
	public Account addAccount(HttpServletRequest request) {
		return accountService.addNewAccount(request.getSession(false));
	}
	
//	@GetMapping("viewaccounts")
//	public List<Account> getAccounts(HttpServletRequest request, Authentication auth) {
//		return accountService.getAccounts(request.getSession(false));
//	}
	@GetMapping("/viewaccounts")
	public Set<Account> getAccounts(Authentication auth){
		return accountService.getAccounts(auth.getName());
	}
	
	@PostMapping("deposit")
	public Transaction deposit(@RequestBody Transaction trans) {
		return accountService.deposit(trans);
	}
	
	@PostMapping("withdraw")
	public Transaction withdraw(@RequestBody Transaction trans) {
		return accountService.withdraw(trans);
	}
	
	@PostMapping("transfer")
	public Transaction transfer(@RequestBody Transaction trans) {
		return accountService.transfer(trans);
	}
}
