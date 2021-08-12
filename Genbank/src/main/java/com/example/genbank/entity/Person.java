package com.example.genbank.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
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
import javax.persistence.Transient;

@Entity
@Table(name="person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	private String username;
//	@Transient
	private String password;
	private String email;
	private String phonenumber;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "accholder",
			joinColumns = @JoinColumn(name = "accuserid"),
			inverseJoinColumns = @JoinColumn(name = "accnumberid")
			)
	private Set<Account> accounts;
	private String role;
	
	public Person() {
		super();
	}
	public Person(int id, String firstname, String lastname, String username, String password, String email,
			String phonenumber,String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
//		passwordToHash(password);
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
//	public byte[] getHash() {
//		return hash;
//	}
//	public void setHash(byte[] hash) {
//		this.hash = hash;
//	}
//	public byte[] getSalt() {
//		return salt;
//	}
//	public void setSalt(byte[] salt) {
//		this.salt = salt;
//	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	private void passwordToHash(String password){
//		SecureRandom random = new SecureRandom();
//		byte[] salt = new byte[16];
//		random.nextBytes(salt);
//		this.setSalt(salt);
//
//		MessageDigest md;
//		try {
//			md = MessageDigest.getInstance("SHA-512");
//			md.update(salt);
//			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//			this.setHash(hashedPassword);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
}
