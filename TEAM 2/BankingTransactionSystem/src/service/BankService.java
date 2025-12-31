package service;

import model.*;
import exception.*;
import java.util.*;

public class BankService {

    private Map<Integer, Account> accounts = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private FraudDetectionService fraudService = new FraudDetectionService();

    public void deposit(int accNo, double amount)
            throws InvalidAccountException, FraudException {

        Account acc = accounts.get(accNo);
        if (acc == null) throw new InvalidAccountException("Account not found");

        Transaction txn = new Transaction(accNo, "DEPOSIT", amount);

        if (fraudService.isSuspicious(txn, transactions)) {
            throw new FraudException("Suspicious deposit detected");
        }

        acc.deposit(amount);
        transactions.add(txn);
    }

    public void withdraw(int accNo, double amount)
            throws InvalidAccountException, InsufficientBalanceException, FraudException {

        Account acc = accounts.get(accNo);
        if (acc == null) throw new InvalidAccountException("Account not found");

        Transaction txn = new Transaction(accNo, "WITHDRAW", amount);

        if (fraudService.isSuspicious(txn, transactions)) {
            throw new FraudException("Suspicious withdrawal detected");
        }

        acc.withdraw(amount);
        transactions.add(txn);
    }
    public void createAccount(Account account) {
    accounts.put(account.getAccountNumber(), account);
}


    public void transfer(int from, int to, double amount)
            throws InvalidAccountException, InsufficientBalanceException, FraudException {

        Account src = accounts.get(from);
        Account tgt = accounts.get(to);

        if (src == null || tgt == null)
            throw new InvalidAccountException("Account not found");

        Transaction txn = new Transaction(from, to, "TRANSFER", amount);

        if (fraudService.isSuspicious(txn, transactions)) {
            throw new FraudException("Suspicious transfer detected");
        }

        src.withdraw(amount);
        tgt.deposit(amount);
        transactions.add(txn);
 
    }
    public double checkBalance(int accNo) throws InvalidAccountException {
    Account acc = accounts.get(accNo);
    if (acc == null) {
        throw new InvalidAccountException("Account not found");
    }
    return acc.getBalance();
}


   public void printTransactions() {
    int size = transactions.size();

    if (size == 0) {
        System.out.println("No transactions found.");
        return;
    }

    System.out.println("Last transactions:");

    // start index = max(size - 3, 0)
    int start = Math.max(size - 3, 0);

    for (int i = start; i < size; i++) {
        System.out.println(transactions.get(i));
    }
}


}
