package com.example.genbank.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.genbank.entity.Account;
import com.example.genbank.entity.Person;
import com.example.genbank.entity.Transaction;
import com.example.genbank.repository.AccountRepository;
import com.example.genbank.repository.PersonRepository;
import com.example.genbank.repository.TransactionRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PersonRepository personRespository;
	
	@Autowired
	TransactionRepository transRepository;
	
	public Account createAccount() {
		Random random = new Random();
		int accountNumber = random.nextInt(9000000) + 1000000; 
		Account newAccount = new Account(0,Integer.toString(accountNumber),0);
		try {
			accountRepository.save(newAccount);
			return newAccount;
		}catch(Exception e) {
			return new Account();
		}
	}
	
	public Set<Account> getAccounts(String username){
		try {
			Person person = personRespository.findByUsername(username);
			return person.getAccounts();
		} catch(Exception e) {
			return null;
		}
	}
	
	public Account addNewAccount(String username) {
		Account a = new Account(0, "", 0);
		try {
			Person person = personRespository.findByUsername(username);
			Account newAccount = createAccount();
			person.getAccounts().add(newAccount);
			personRespository.save(person);
			return newAccount;
		} catch(Exception e) {
			return a;
		}
	}
	
	public Transaction deposit(Transaction trans) {
		try {
			Account a = accountRepository.findByAccountnumber(trans.getAccountto());
			if(a != null) {
				double balance = a.getBalance() + trans.getAmount();
				updateAccAndTrans(a, trans, balance);
				trans.setMessage("Success");
				return trans;
			}
			trans.setMessage("Failed");
			return trans;
		}catch(Exception e){
			trans.setMessage("Failed");
			return trans;
		}
	}
	
	public Transaction withdraw(Transaction trans) {
		try {
			Account a = accountRepository.findByAccountnumber(trans.getAccountto());
			if(a != null) {
				double balance = a.getBalance() - trans.getAmount();
				updateAccAndTrans(a, trans, balance);
				trans.setMessage("Success");
				return trans;
			}
			trans.setMessage("Failed");
			return trans;
		}catch(Exception e){
			trans.setMessage("Failed");
			return trans;
		}
	}
	
	public Account transfer(Account account, String transferTo) {
		try {
			Account fromAcc = accountRepository.findByAccountnumber(account.getAccountnumber());
			Account toAcc = accountRepository.findByAccountnumber(transferTo);
			fromAcc.setBalance(fromAcc.getBalance() - account.getBalance());
			toAcc.setBalance(toAcc.getBalance() + account.getBalance());
			accountRepository.save(fromAcc);
			accountRepository.save(toAcc);
			return fromAcc;
		}catch(Exception e) {
			return new Account(0,"",0);
		}
	}
	
	public Transaction transfer(Transaction trans) {
		try {
			Transaction secTrans = new Transaction(trans);
			Account fromAcc = accountRepository.findByAccountnumber(trans.getAccountfrom());
			Account toAcc = accountRepository.findByAccountnumber(trans.getAccountto());
			if(fromAcc != null && toAcc != null) {
				double balance = fromAcc.getBalance() - trans.getAmount();
				updateAccAndTrans(fromAcc, trans, balance);
				balance = toAcc.getBalance() + trans.getAmount();
				updateAccAndTrans(toAcc, secTrans, balance);
				trans.setMessage("Success");
				return trans;
			}
			trans.setMessage("Failed");
			return trans;
		}catch(Exception e) {
			trans.setMessage("Failed");
			return trans;
		}
	}
	
	public void updateAccAndTrans(Account acc, Transaction trans, double balance) {
		trans.setBalance(balance);
		trans.setTransdate(LocalDate.now());
		Set<Transaction> transList = acc.getTransaction();
		if(transList == null) {
			transList = new HashSet<Transaction>();
		}
		transList.add(trans);
		acc.setBalance(balance);
		acc.setTransaction(transList);
		transRepository.save(trans);
		accountRepository.save(acc);
	}
}
