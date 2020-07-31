package com.example.genbank.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.genbank.entity.Account;
import com.example.genbank.entity.Person;
import com.example.genbank.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AccountService accountService;
	
	private HttpSession httpSession;
	
	public Person registerUser(Person person) {
		Person newUser = new Person(0,person.getFirstname(),person.getLastname(),person.getUsername(),person.getPassword(),person.getEmail(),person.getPhonenumber(),"customer");
		try {
			personRepository.save(newUser);
			newUser = personRepository.findByUsername(person.getUsername());
			Account newAccount = accountService.createAccount();
			Set<Account> accounts = newUser.getAccounts();
			if(accounts == null) {
				accounts = new HashSet<Account>();
			}
			accounts.add(newAccount);
			newUser.setAccounts(accounts);
			personRepository.save(newUser);
			return person;
		}catch(Exception e){
			Person p = new Person();
			return p;
		}
	}
	
	public Person loginUser(Person person, HttpSession session) {
		Person p = new Person(0,"","","","","","","");
		try {
			Person loginPerson = personRepository.findByUsername(person.getUsername());
			MessageDigest md;
				md = MessageDigest.getInstance("SHA-512");
				md.update(loginPerson.getSalt());
				byte[] hashedPassword = md.digest(person.getPassword().toString().getBytes(StandardCharsets.UTF_8));
				if (Arrays.equals(hashedPassword, loginPerson.getHash())) {
					setSession(loginPerson.getUsername(), loginPerson.getId(), loginPerson.getRole(),session);
					return loginPerson;
				}
				return p;
		}catch(Exception e) {
			return p;
		}
	}
	
	public void setSession(String username, int id, String role, HttpSession session) {
		session.setAttribute("username", username);
		session.setAttribute("id", Integer.toString(id));
		session.setAttribute("role",role);
	}
	
//	public void setHttpSession(String username, int id, String role) {
//		httpSession.setAttribute("username", username);
//		httpSession.setAttribute("id", Integer.toString(id));
//		httpSession.setAttribute("role",role);
//	}
}
