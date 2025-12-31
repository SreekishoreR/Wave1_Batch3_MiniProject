package model;

import exception.InsufficientBalanceException;

public abstract class Account {
    protected int accountNumber;
    protected Customer customer;
    protected double balance;

    public Account(int accountNumber, Customer customer, double balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
        throw new IllegalArgumentException("Deposit amount must be positive");
    }
    balance += amount;
}

public abstract void withdraw(double amount)
        throws InsufficientBalanceException;}
