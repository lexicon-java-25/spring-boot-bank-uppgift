package com.example.bankuppgift.contoller;

import com.example.bankuppgift.dto.AccountRequest;
import com.example.bankuppgift.dto.TransferRequest;
import com.example.bankuppgift.model.Account;
import com.example.bankuppgift.model.Transaction;
import com.example.bankuppgift.service.AccountService;
import com.example.bankuppgift.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private final AccountService service;
    private final TransactionService transactionService;


    public AccountController(AccountService service, TransactionService transactionService)
    {
        this.service = service;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll()
    {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody AccountRequest request)
    {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request)
    {
        service.transfer(request);
        return ResponseEntity.ok("Transaction completed");
    }


    @GetMapping("/transactions")
    public List<Transaction> transactions()
    {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Account>> search(@PathVariable String name)
    {
        return ResponseEntity.ok(service.searchByName(name));
    }

    @GetMapping("/findbybalance/{balance}")
    public ResponseEntity<Page<Account>> search(@PathVariable double balance,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "1") int pageSize,
                                                @RequestParam(defaultValue = "balance") String sortBy
    )
    {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        return ResponseEntity.ok(service.findByMinBalance(balance, pageable));
    }

}
