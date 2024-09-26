package org.geektext.model;

import jakarta.persistence.*;

@Entity
public class CreditCard {

    @Id
    private long cardNumber;
    private int cvv;
    private int expDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard(){}

    public CreditCard(long cardNumber, int cvv, int expDate, User user) {
        this.user = user;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expDate = expDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public int getExpDate() {
        return expDate;
    }

}
