package com.example.bankuppgift.service;


import com.example.bankuppgift.dto.AccountRequest;
import com.example.bankuppgift.dto.TransferRequest;
import com.example.bankuppgift.exception.InsufficientFundsException;
import com.example.bankuppgift.exception.ResourceNotFoundException;
import com.example.bankuppgift.model.Account;
import com.example.bankuppgift.model.Transaction;
import com.example.bankuppgift.repository.AccountRepository;
import com.example.bankuppgift.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService
{

    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;



    public AccountService(AccountRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }




    public List<Account> getAll()
    {
        return repository.findAll();
    }


    public Account create(AccountRequest request)
    {
        Account account = new Account(request.getOwnerName(), request.getBalance());
        return repository.save(account);
    }


    public Account getById(Long id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public void transfer(TransferRequest request)
    {
        Account from = getById(request.getFromAccountId());
        Account to = getById(request.getToAccountId());

        if (from.getBalance() < request.getAmount())
        {
            throw new InsufficientFundsException("You do not have the money to do this transfer, do it on the 25th :)");
        }

        from.setBalance(from.getBalance() - request.getAmount());
        to.setBalance(to.getBalance() + request.getAmount());
        transactionRepository.save(new Transaction(from.getOwnerName(), to.getOwnerName(), request.getAmount()));
    }

    public List<Account> searchByName(String name)
    {
        return repository.searchByName(name);
    }

    public Page<Account> findByMinBalance(double minBalance, Pageable pageable)
    {
        return repository.findByMinBalance(minBalance, pageable);
    }

}
