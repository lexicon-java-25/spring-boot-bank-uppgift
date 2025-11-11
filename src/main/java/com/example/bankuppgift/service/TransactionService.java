package com.example.bankuppgift.service;

import com.example.bankuppgift.model.Transaction;
import com.example.bankuppgift.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService
{

    public final TransactionRepository repository;


    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }



    public List<Transaction> getAllTransactions()
    {
        return repository.findAll();
    }

}
