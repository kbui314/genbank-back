package com.example.genbank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.genbank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

	public Account findByAccountnumber(String accountnumber);
}
