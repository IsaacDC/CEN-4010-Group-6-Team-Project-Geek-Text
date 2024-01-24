package org.geektext.controller;


import org.geektext.model.User;
import org.geektext.repository.UserRepository;
import org.geektext.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserService userServiceTest;

    @GetMapping("/loadUsers")
    public String loadUsers(){
        // Loads 3 test User objects to database

        userServiceTest.addUsers();
        return "Users Loaded to Database";
    }



    @Autowired
    private UserRepository userRepository;

    @GetMapping("/showUsers")
    public List<User> getAllUsers() {
        // *Testing* - Returns list of saved users

        return userRepository.findAll();
    }


}
