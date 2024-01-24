package org.geektext.repository;

import org.geektext.model.CreditCard;
import org.geektext.model.User;

public interface CreditCardRepository {

    void insertCard(CreditCard card);

    void insertCard(CreditCard card, int userId);
}