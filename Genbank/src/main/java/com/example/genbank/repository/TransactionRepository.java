package com.example.genbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.genbank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

}
