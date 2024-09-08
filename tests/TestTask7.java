import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    void testTransferSameUserSameBank() {
        Bank bank = new Bank("Bank A", "USD");
        User user = new User("John Doe");
        BankAccount account1 = bank.createAccount(user, 1000);
        BankAccount account2 = bank.createAccount(user, 500);

        account1.transferTo(account2, 100);

        assertEquals(900, account1.getBalance());
        assertEquals(600, account2.getBalance());
    }

    @Test
    void testTransferSameUserDifferentBank() {
        Bank bank1 = new Bank("Bank A", "USD");
        Bank bank2 = new Bank("Bank B", "EUR");
        User user = new User("John Doe");
        BankAccount account1 = bank1.createAccount(user, 1000);
        BankAccount account2 = bank2.createAccount(user, 500);

        account1.transferTo(account2, 100);

        double expectedBalanceAccount1 = 1000 - 100 - (100 * 0.02);
        double convertedAmount = 100 * Bank.getExchangeRate("USD", "EUR");

        assertEquals(expectedBalanceAccount1, account1.getBalance());
        assertEquals(500 + convertedAmount, account2.getBalance());
    }

    @Test
    void testTransferDifferentUserSameBank() {
        Bank bank = new Bank("Bank A", "USD");
        User user1 = new User("John Doe");
        User user2 = new User("Jane Smith");
        BankAccount account1 = bank.createAccount(user1, 1000);
        BankAccount account2 = bank.createAccount(user2, 500);

        account1.transferTo(account2, 100);

        double expectedBalanceAccount1 = 1000 - 100 - (100 * 0.03);
        assertEquals(expectedBalanceAccount1, account1.getBalance());
        assertEquals(600, account2.getBalance());
    }

    @Test
    void testTransferDifferentUserDifferentBank() {
        Bank bank1 = new Bank("Bank A", "USD");
        Bank bank2 = new Bank("Bank B", "EUR");
        User user1 = new User("John Doe");
        User user2 = new User("Jane Smith");
        BankAccount account1 = bank1.createAccount(user1, 1000);
        BankAccount account2 = bank2.createAccount(user2, 500);

        account1.transferTo(account2, 100);

        double expectedBalanceAccount1 = 1000 - 100 - (100 * 0.06);
        double convertedAmount = 100 * Bank.getExchangeRate("USD", "EUR");

        assertEquals(expectedBalanceAccount1, account1.getBalance());
        assertEquals(500 + convertedAmount, account2.getBalance());
    }

    @Test
    void testInsufficientFunds() {
        Bank bank = new Bank("Bank A", "USD");
        User user = new User("John Doe");
        BankAccount account1 = bank.createAccount(user, 50);
        BankAccount account2 = bank.createAccount(user, 500);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> account1.transferTo(account2, 100));
        assertEquals("Недостатньо коштів для переказу.", thrown.getMessage());
    }

    @Test
    void testNegativeTransferAmount() {
        Bank bank = new Bank("Bank A", "USD");
        User user = new User("John Doe");
        BankAccount account1 = bank.createAccount(user, 1000);
        BankAccount account2 = bank.createAccount(user, 500);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> account1.transferTo(account2, -100));
        assertEquals("Сума переказу повинна бути більше нуля.", thrown.getMessage());
    }

    @Test
    void testNullTargetAccount() {
        Bank bank = new Bank("Bank A", "USD");
        User user = new User("John Doe");
        BankAccount account1 = bank.createAccount(user, 1000);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> account1.transferTo(null, 100));
        assertEquals("Цільовий рахунок не може бути null.", thrown.getMessage());
    }
}
