package model;

import exception.InsufficientBalanceException;

public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance - amount < 500) {
            throw new InsufficientBalanceException("Minimum balance should be 500 after withdrawal");
        }
        balance -= amount;
    }
}
