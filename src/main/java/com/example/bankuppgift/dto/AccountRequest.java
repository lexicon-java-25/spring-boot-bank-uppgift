package com.example.bankuppgift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AccountRequest
{
    @NotBlank(message = "Owner name must be specified")
    private String ownerName;

    @Min(value = 0, message = "Account balance must start at 0")
    private double balance;


    public AccountRequest(String ownerName, double balance)
    {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
