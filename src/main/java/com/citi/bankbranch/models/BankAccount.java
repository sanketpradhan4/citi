package com.citi.bankbranch.models;

import java.math.BigDecimal;

public class BankAccount {
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
    }

    public BankAccount(int accountNumber,BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    

    private int accountNumber;
    private BigDecimal balance;

    public boolean withdrawAmount(BigDecimal amount) {
        if(balance.compareTo(amount) >=0){
            balance=balance.subtract(amount);
            return true;
        }
        return false;
    }

    public void depositAmount(BigDecimal amount) {
        balance=balance.add(amount);
    }
}
