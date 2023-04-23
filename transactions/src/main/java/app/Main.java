package app;

import domain.Account;

import java.math.BigDecimal;

public class  Main {
    public static void main(String[] args) {
        var account = new Account("iban", BigDecimal.TEN, BigDecimal.valueOf(100),null);
        account.deposit(BigDecimal.valueOf(1_000));
        account.withdraw(BigDecimal.valueOf(101));
        account.withdraw(BigDecimal.valueOf(100));
        account.setLimit(BigDecimal.TEN.pow(9));
        account.withdraw(BigDecimal.valueOf(101));
    }
}
