package com.citi.bankbranch.service;

import com.citi.bankbranch.models.BankAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Component
public class Operations {
    private final Map<Integer, BankAccount> accountsMap;

    public Operations(){
        accountsMap = new TreeMap<>();
        accountsMap.put(999,new BankAccount(999,new BigDecimal("1000")));
    }
    public void openAccount(int acctNum) {
        if(accountsMap.containsKey(acctNum)){
            System.err.println("Account number "+acctNum+" is already opened");
            return;
        }
        accountsMap.put(acctNum,new BankAccount(acctNum));
    }

    public void displayAcctNums() {
        accountsMap.forEach((K,V)-> System.out.println(K));
    }

    public void acctTransfer(int srcAcct, int destAcct, BigDecimal amount) {
        if(!accountsMap.containsKey(srcAcct) || !accountsMap.containsKey(destAcct)){
            System.err.println("Check Account Numbers");
            return;
        }
        BankAccount sourceAccount = accountsMap.get(srcAcct);
        BankAccount destinationAccount = accountsMap.get(destAcct);
        if(sourceAccount.withdrawAmount(amount)){
            destinationAccount.depositAmount(amount);
            //System.out.println("Transfer success - to be removed");
        }
        else System.err.println("Insufficient balance");
    }


    public void displayBalance(int balAcctNum) {
        if(!accountsMap.containsKey(balAcctNum)){
            System.err.println("Invalid account number");
        }
        else{
            BankAccount acctNum = accountsMap.get(balAcctNum);
            System.out.println(acctNum.getBalance());
        }
    }

    public void closeAcctNum(int closeAcctNum) {
        if(closeAcctNum == 999) {
            System.err.println("Account cannot be closed");
            return;
        }
        if(!accountsMap.containsKey(closeAcctNum)){
            System.err.println("Invalid Account Number");
        }
        BankAccount acctToBeClosed = accountsMap.get(closeAcctNum);
        if(acctToBeClosed.getBalance().compareTo(BigDecimal.ZERO) != 0){
            System.err.println("Account cannot be closed");
        }
        else {
            accountsMap.remove(closeAcctNum);
            //System.out.println("account closed - to be removed");
        }
    }

}
