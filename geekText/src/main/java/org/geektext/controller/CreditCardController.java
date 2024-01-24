package org.geektext.controller;

import org.geektext.repository.CreditCardRepository;
import org.geektext.service.CreditCardService;
import org.geektext.service.UserService;
import org.geektext.model.CreditCard;
import org.geektext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CreditCardController {

   @Autowired
   CreditCardRepository creditCardRepo;
   @Autowired
   UserService userService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService, UserService userService){
        this.userService = userService;
        this.creditCardRepo = creditCardService;
    }

    @PostMapping("/{username}/addcreditcard")
    public ResponseEntity<String> insertCreditCard(@PathVariable("username") String username, @RequestBody CreditCard card){
        try{

            User user = userService.selectUserByUsername(username);

            if (user == null){
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            card.setUser(user);
            creditCardRepo.insertCard(new CreditCard(card.getCardNumber(), card.getCvv(), card.getExpDate(), user));
            return ResponseEntity.status(HttpStatus.CREATED).body("CreditCard was created successfully");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
