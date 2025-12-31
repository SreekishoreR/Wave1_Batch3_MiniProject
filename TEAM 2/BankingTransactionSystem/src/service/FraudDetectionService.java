package service;

import model.Transaction;
import java.util.List;

public class FraudDetectionService {

    private static final double MAX_AMOUNT = 50000;

    public boolean isSuspicious(Transaction txn, List<Transaction> history) {

        // Rule 1: Large transaction
        if (txn.getAmount() > MAX_AMOUNT) {
            return true;
        }

        // Rule 2: Too many transactions in short time
        long recentCount = history.stream()
                .filter(t -> t.getFromAccount() == txn.getFromAccount())
                .filter(t -> t.isWithinLastMinutes(1))
                .count();

        return recentCount >= 3;
    }
}
