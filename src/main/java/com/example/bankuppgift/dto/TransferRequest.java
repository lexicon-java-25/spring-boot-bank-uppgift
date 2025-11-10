package com.example.bankuppgift.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

    @NotNull(message = "From account id is needed")
    private Long fromAccountId;

    @NotNull(message = "To account id is needed")
    private Long toAccountId;

    @Min(value = 0, message = "Account balance must start at 0")
    private double amount;


    public TransferRequest(Long fromAccountId, Long toAccountId, double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
