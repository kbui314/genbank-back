package com.example.genbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.genbank.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer>{

	Person findById(int id);
	
	Person findByUsername(String username);
}
