package org.geektext.repository;
import org.geektext.model.CreditCard;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository {

    void insertCard(CreditCard card);

    void insertCard(CreditCard card, int userId);
}