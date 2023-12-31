package com.drunkleen.crudmvc.services;

import com.drunkleen.crudmvc.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    List<Transaction> findAllSortByIdAsc();

    Transaction findById(Integer id);

    Transaction save(Transaction transaction);

    void deleteById(Integer id);
}
