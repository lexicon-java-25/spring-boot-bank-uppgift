package com.example.bankuppgift.contoller;

import com.example.bankuppgift.dto.AccountRequest;
import com.example.bankuppgift.dto.TransferRequest;
import com.example.bankuppgift.model.Account;
import com.example.bankuppgift.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private final AccountService service;


    public AccountController(AccountService service)
    {
        this.service = service;
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


}
