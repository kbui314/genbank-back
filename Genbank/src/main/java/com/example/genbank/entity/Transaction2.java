package com.example.genbank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction2")
public class Transaction2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transid;

	private LocalDate transdate;

	private String accountfrom;

	private String accountto;

	private double amount;

	private String transtype;

	private double balance;
	
	private String message;

	public Transaction2() {
		super();
	}

	public Transaction2(int transid, LocalDate transdate, String accountfrom, String accountto, double amount,
			String transtype, double balance) {
		super();
		this.transid = transid;
		this.transdate = transdate;
		this.accountfrom = accountfrom;
		this.accountto = accountto;
		this.amount = amount;
		this.transtype = transtype;
		this.balance = balance;
	}
	
	public Transaction2(Transaction2 trans) {
		super();
		this.transid = trans.getTransId();
		this.transdate = trans.getTransdate();
		this.accountfrom = trans.getAccountfrom();
		this.accountto = trans.getAccountto();
		this.amount = trans.getAmount();
		this.transtype = trans.getTranstype();
		this.balance = trans.getBalance();
	}

	public int getTransId() {
		return transid;
	}

	public void setTransId(int transid) {
		this.transid = transid;
	}

	public LocalDate getTransdate() {
		return transdate;
	}

	public void setTransdate(LocalDate transdate) {
		this.transdate = transdate;
	}

	public String getAccountfrom() {
		return accountfrom;
	}

	public void setAccountfrom(String accountfrom) {
		this.accountfrom = accountfrom;
	}

	public String getAccountto() {
		return accountto;
	}

	public void setAccountto(String accountto) {
		this.accountto = accountto;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
