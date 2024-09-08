// O(1)

import java.util.HashMap;
import java.util.Map;

public class BankSystemTask7 {

    public static void main(String[] args) {
        try {
            final Bank bank1 = new Bank("Bank A", "USD");
            final Bank bank2 = new Bank("Bank B", "EUR");

            final User user1 = new User("Zakhar Nazarko");
            final User user2 = new User("Katya Losova");

            final BankAccount account1 = bank1.createAccount(user1, 1000);
            final BankAccount account2 = bank1.createAccount(user1, 500);
            final BankAccount account3 = bank2.createAccount(user1, 2000);
            final BankAccount account4 = bank1.createAccount(user2, 1500);

            account1.transferTo(account2, 100);

            account1.transferTo(account3, 200);

            account1.transferTo(account4, 50);

            account3.transferTo(account4, 300);

            System.out.println("Баланс Account1: " + account1.getBalance() + " " + account1.getCurrency());
            System.out.println("Баланс Account2: " + account2.getBalance() + " " + account2.getCurrency());
            System.out.println("Баланс Account3: " + account3.getBalance() + " " + account3.getCurrency());
            System.out.println("Баланс Account4: " + account4.getBalance() + " " + account4.getCurrency());

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }
}

record User(String name) {
    User {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ім'я користувача не може бути порожнім.");
        }
    }
}

record Bank(String name, String currency) {
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USDtoEUR", 0.93);
        exchangeRates.put("EURtoUSD", 1.08);
    }

    Bank(final String name, final String currency) {
        if (name == null || name.isEmpty() || currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Назва банку та валюта не можуть бути порожніми.");
        }
        this.name = name;
        this.currency = currency.toUpperCase();
    }

    public BankAccount createAccount(final User user, final double initialBalance) {
        return new BankAccount(user, this, initialBalance);
    }


    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }
        String key = fromCurrency + "to" + toCurrency;
        Double rate = exchangeRates.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("Невідомий курс обміну між " + fromCurrency + " і " + toCurrency);
        }
        return rate;
    }
}

class BankAccount {
    private final User owner;
    private final Bank bank;
    private double balance;

    public BankAccount(final User owner, final Bank bank, final double initialBalance) {
        if (owner == null || bank == null) {
            throw new IllegalArgumentException("Власник та банк не можуть бути null.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Початковий баланс не може бути від'ємним.");
        }
        this.owner = owner;
        this.bank = bank;
        this.balance = initialBalance;
    }

    public User getOwner() {
        return owner;
    }

    public Bank getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return bank.currency();
    }

    public void transferTo(final BankAccount targetAccount, final double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Цільовий рахунок не може бути null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сума переказу повинна бути більше нуля.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Недостатньо коштів для переказу.");
        }

        double commissionRate = calculateCommission(targetAccount);
        double commissionAmount = amount * commissionRate;
        double totalDebit = amount + commissionAmount;

        if (totalDebit > this.balance) {
            throw new IllegalArgumentException("Недостатньо коштів для переказу з урахуванням комісії.");
        }

        this.balance -= totalDebit;

        double exchangeRate = Bank.getExchangeRate(this.getCurrency(), targetAccount.getCurrency());
        double convertedAmount = amount * exchangeRate;

        targetAccount.balance += convertedAmount;
    }

    private double calculateCommission(final BankAccount targetAccount) {
        boolean sameUser = this.owner.equals(targetAccount.owner);
        boolean sameBank = this.bank.equals(targetAccount.bank);

        if (sameUser && sameBank) {
            return 0.0;
        } else if (sameUser) {
            return 0.02;
        } else if (sameBank) {
            return 0.03;
        } else {
            return 0.06;
        }
    }
}
