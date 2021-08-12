package com.example.genbank.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	@PostMapping("/login")
//	public Person login(@RequestBody Person person, HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		return personService.loginUser(person, (request.getSession(false)==null) ? request.getSession(true):request.getSession(false));
//	}
	
//	public void setSession(String username, int id, String role, HttpSession session) {
//		session.setAttribute("username", username);
//		session.setAttribute("id", Integer.toString(id));
//		session.setAttribute("role",role);
//	}
	
	@GetMapping("/logout")
	public Person logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return null;
	}

}
