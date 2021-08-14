package com.example.genbank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.genbank.entity.Person;
import com.example.genbank.service.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {	
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/register")
	public Person register(@RequestBody Person person) {
		return personService.registerUser(person);
	}
	
//	@GetMapping("/logout")
//	public Person logout(HttpServletRequest request) {
//		request.getSession().invalidate();
//		return null;
//	}

}
