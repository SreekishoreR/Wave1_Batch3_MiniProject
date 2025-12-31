package model;

import java.time.LocalDateTime;

public class Transaction {

    private int fromAccount;
    private Integer toAccount;
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    // Deposit / Withdraw
    public Transaction(int fromAccount, String type, double amount) {
        this.fromAccount = fromAccount;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Transfer
    public Transaction(int fromAccount, int toAccount,
                       String type, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public boolean isWithinLastMinutes(int minutes) {
        return timestamp.isAfter(
                LocalDateTime.now().minusMinutes(minutes)
        );
    }

    @Override
    public String toString() {
        return timestamp + " | " + type +
               " | Amount: " + amount;
    }
}
