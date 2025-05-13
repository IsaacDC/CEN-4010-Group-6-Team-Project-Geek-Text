package org.geektext.controller;

import org.geektext.service.CreditCardService;
import org.geektext.service.UserService;
import org.geektext.model.CreditCard;
import org.geektext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/creditcard")
@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;
    @Autowired
    UserService userService;

    public CreditCardController(CreditCardService creditCardService, UserService userService) {
        this.userService = userService;
        this.creditCardService = creditCardService;
    }

    @PostMapping("/{username}/addcreditcard")
    public ResponseEntity<CreditCard> insertCreditCard(@PathVariable String username,
            @RequestBody CreditCard card) {

        User user = userService.findUserByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        card.setUser(user);
        creditCardService.insertCard(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }
}
