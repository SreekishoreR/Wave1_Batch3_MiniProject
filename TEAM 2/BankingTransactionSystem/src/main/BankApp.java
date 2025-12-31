package main;

import exception.InvalidAccountException;
import exception.InsufficientBalanceException;
import java.util.Scanner;
import model.*;
import service.BankService;
import exception.FraudException;


public class BankApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Print Transactions");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Customer ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    System.out.print("Account Type (1-Savings, 2-Current): ");
                    int type = sc.nextInt();

                    Customer customer = new Customer(cid, name);
                    Account account;

                    if (type == 1) {
                        account = new SavingsAccount(accNo, customer, balance);
                    } else {
                        account = new CurrentAccount(accNo, customer, balance);
                    }

                    bank.createAccount(account);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
    try {
        System.out.print("Enter Account Number: ");
        accNo = sc.nextInt();

        System.out.print("Enter Deposit Amount: ");
        double depAmt = sc.nextDouble();

        bank.deposit(accNo, depAmt);
        System.out.println("Deposit successful!");
    } catch (FraudException e) {
    System.out.println("⚠ FRAUD ALERT: " + e.getMessage());
} catch (InvalidAccountException | IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}

    break;


                case 3:
    try {
        System.out.print("Enter Account Number: ");
        accNo = sc.nextInt();

        System.out.print("Enter Withdraw Amount: ");
        double wAmt = sc.nextDouble();

        bank.withdraw(accNo, wAmt);
        System.out.println("Withdrawal successful!");
    } catch (FraudException e) {
    System.out.println("⚠ FRAUD ALERT: " + e.getMessage());
} catch (InvalidAccountException | InsufficientBalanceException | IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}

    break;


                case 4:
    try {
        System.out.print("From Account: ");
        int from = sc.nextInt();

        System.out.print("To Account: ");
        int to = sc.nextInt();

        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        bank.transfer(from, to, amt);
        System.out.println("Transfer successful!");
    } catch (FraudException e) {
    System.out.println("⚠ FRAUD ALERT: " + e.getMessage());
} catch (InvalidAccountException | InsufficientBalanceException | IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}

    break;


                case 5:
                    bank.printTransactions();
                    break;
                case 6:
                    
    try {
        System.out.print("Enter Account Number: ");
        accNo = sc.nextInt();

        double bal = bank.checkBalance(accNo);
        System.out.println("Current Balance:
        " + bal);
    } catch (InvalidAccountException e) {
        System.out.println("Error: " + e.getMessage());
    }
    break;


                case 7:
                    System.out.println("Thank you for using Banking System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
