package model;

import exception.InsufficientBalanceException;

public class CurrentAccount extends Account {

    public CurrentAccount(int accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }
}
