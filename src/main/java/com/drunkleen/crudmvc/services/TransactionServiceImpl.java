package com.drunkleen.crudmvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.drunkleen.crudmvc.domain.Transaction;
import com.drunkleen.crudmvc.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }



    @Override
    public List<Transaction> findAllSortByIdAsc() {
        return transactionRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Transaction findById(Integer id) {
        Optional<Transaction> result = transactionRepository.findById(id);
        Transaction transaction = null;

        if (result.isEmpty()) {
            throw new RuntimeException("Couldn't find transaction with id " + id);
        }
        transaction = result.get();

        return transaction;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }

}
