package com.example.genbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.genbank.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer>{

	public Person findById(int id);
	
	public Person findByUsername(String username);
}
