package com.example.genbank.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountid;
	
	private String accountnumber;
	
	private double balance;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "transholder",
			joinColumns = @JoinColumn(name = "accnumberid"),
			inverseJoinColumns = @JoinColumn(name = "transactionid"))
	private Set<Transaction> transactions;
	
	public Account() {
		super();
	}

	public Account(int accountid, String accountnumber, double balance) {
		super();
		this.accountid = accountid;
		this.accountnumber = accountnumber;
		this.balance = balance;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Transaction> getTransaction() {
		return transactions;
	}

	public void setTransaction(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
