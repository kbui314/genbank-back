package com.example.genbank.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.genbank.entity.Account;
import com.example.genbank.entity.Person;
import com.example.genbank.repository.PersonRepository;

@Service
public class PersonService implements UserDetailsService {
	
	private PersonRepository personRepository;
	
	private AccountService accountService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public PersonService(PersonRepository personRepository, AccountService accountService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.personRepository = personRepository;
		this.accountService = accountService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Person person = personRepository.findByUsername(username);
		
		if(person == null) {
			throw new UsernameNotFoundException("Person " + username + " not found");
		}
		
		if(person.getRole() == "ADMIN") {
			return org.springframework.security.core.userdetails.User
					.withUsername(username)
					.password(person.getPassword())
					.authorities("ADMIN")
					.accountExpired(false)
					.accountLocked(false)
					.credentialsExpired(false)
					.disabled(false)
					.build();
		}
		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(person.getPassword())
				.authorities("USER")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
	
	public Person registerUser(Person person) {
		Person newUser = new Person(0,person.getFirstname(),person.getLastname(),person.getUsername(),person.getPassword(),person.getEmail(),person.getPhonenumber(),"USER");
		try {
			String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			newUser.setRole("USER");
			newUser.setPassword(encryptedPassword);
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
}
