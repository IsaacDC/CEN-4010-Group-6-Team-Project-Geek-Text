package org.geektext.service;


import org.geektext.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.geektext.repository.CreditCardRepository;

@Service
public class CreditCardService implements CreditCardRepository {

    @Autowired
    public void CreditCardRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertCard(CreditCard card) {
        jdbcTemplate.update("INSERT INTO creditcard(cardNumber, fullname, cvv, expDate, username, userID) VALUES(?,?,?,?,?,?)",
                card.getCardNumber(), card.getUser().getFullname(), card.getCvv(), card.getExpDate(), card.getUser().getUsername(), card.getUser().getId());

    }

    @Override
    public void insertCard(CreditCard card, int userId) {

    }


}