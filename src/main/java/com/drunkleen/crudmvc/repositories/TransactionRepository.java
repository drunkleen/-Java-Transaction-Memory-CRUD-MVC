package com.drunkleen.crudmvc.repositories;

import com.drunkleen.crudmvc.domain.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findAllByOrderByIdAsc();
}
