package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.*;
import service.BankService;

public class BankServiceTest {

    @Test
    public void testDeposit() throws Exception {
        BankService bank = new BankService();
        Customer c = new Customer(1, "Test");
        Account acc = new SavingsAccount(101, c, 1000);
        bank.createAccount(acc);

        bank.deposit(101, 500);
        assertEquals(1500, acc.getBalance(), 0);
    }
}
