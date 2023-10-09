package com.citi.bankbranch;

import com.citi.bankbranch.service.Operations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class BankBranchCommandLineRunner implements CommandLineRunner {
    private final Operations ops;
    public BankBranchCommandLineRunner (Operations ops){
        this.ops = ops;
    }
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            String[] operation = input.split(" ");
            switch (operation[0]) {
                case "O" :
                    int acctNum = Integer.parseInt(operation[1]);
                    if(!accountCheck(acctNum)) {
                        System.err.println("Account Number should be positive integer");
                        break;
                    }
                    ops.openAccount(acctNum);
                    break;
                case "T" :
                    int srcAcct = Integer.parseInt(operation[1]);
                    if(!accountCheck(srcAcct)) {
                        System.err.println("Account Number should be positive");
                        break;
                    }
                    int destAcct = Integer.parseInt(operation[2]);
                    if(!accountCheck(destAcct)) {
                        System.err.println("Account Number should be positive");
                        break;
                    }
                    BigDecimal amount = new BigDecimal(operation[3]);
                    if(amount.compareTo(BigDecimal.ZERO) < 0){
                        System.err.println("Account Number should be positive integer");
                    }
                    ops.acctTransfer(srcAcct,destAcct,amount);
                    break;
                case "C" :
                    int closeAcctNum = Integer.parseInt(operation[1]);
                    if(!accountCheck(closeAcctNum)) {
                        System.err.println("Account Number should be positive integer");
                        break;
                    }
                    ops.closeAcctNum(closeAcctNum);
                    break;
                case "B" :
                    int balAcctNum = Integer.parseInt(operation[1]);
                    if(!accountCheck(balAcctNum)){
                        System.err.println("Account Number should be positive integer");
                        break;
                    }
                    ops.displayBalance(balAcctNum);
                    break;
                case "L":
                    ops.displayAcctNums();
                    break;
                case "Q" :
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Operation");
            }
        }
    }

    private boolean accountCheck(int acctNum){
        return acctNum > 0;
    }
}
