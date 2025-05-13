package org.geektext.repository;
import org.geektext.model.CreditCard;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository {

    CreditCard insertCard(CreditCard card);

}