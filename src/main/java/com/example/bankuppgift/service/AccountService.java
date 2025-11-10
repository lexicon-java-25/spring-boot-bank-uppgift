package com.example.bankuppgift.service;


import com.example.bankuppgift.dto.AccountRequest;
import com.example.bankuppgift.dto.TransferRequest;
import com.example.bankuppgift.exception.InsufficientFundsException;
import com.example.bankuppgift.exception.ResourceNotFoundException;
import com.example.bankuppgift.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService
{

    private final List<Account> accounts = new ArrayList<>();

    private final AtomicLong idCounter = new AtomicLong(1);


    public List<Account> getAll()
    {
        return accounts;
    }


    public Account create(AccountRequest request)
    {
        Account account = new Account(idCounter.getAndIncrement(), request.getOwnerName(), request.getBalance());
        accounts.add(account);
        return account;
    }


    public Account getById(Long id)
    {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
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
    }


}
