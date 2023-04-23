package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor

public class Account {

    @Getter
    private final String iban;
    @Getter
    private BigDecimal balance;
    @Getter
    private BigDecimal limit;

    List<Subscriber> subscribers;

    /**
     * Increases the balance by a given amount.
     *
     * @param amount amount to be deposited
     * @throws IllegalArgumentException if amount is < 0
     */
    public void deposit(BigDecimal amount) {
        subscribers.forEach(Subscriber::notifyAllSub);
    }

    /**
     * Decreases the balance by a given amount. The amount must be 0 <= amount <= limit.
     *
     * @param amount amount to be withdrawn
     * @throws IllegalArgumentException if amount < 0 or amount > limit
     */
    public void withdraw(BigDecimal amount) {
        subscribers.forEach(Subscriber::notifyAllSub);

    }

    /**
     * Sets a new limit.
     *
     * @param limit the desired limit
     * @throws IllegalArgumentException if limit is < 0
     */
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
    public void addSub(Subscriber subscriber){
        subscribers.add(subscriber);
    }
}
