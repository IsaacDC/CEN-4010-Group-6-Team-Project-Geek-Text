package org.geektext.controller;

import java.util.List;

import org.geektext.repository.UserRepository;
import org.geektext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @PostMapping("/user/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        try {
            userRepo.insertUser(new User(user.getId(), user.getAddress(), user.getFullname(), user.getPassword(),
                    user.getUsername()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        try {

            List<User> users = userRepo.findAllUsers();

            if (users.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userRepo.findUserByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        try {
            userRepo.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{username}/update")
    public ResponseEntity<Void> updateUser(@PathVariable String username,
            @RequestBody User updatedUser) {
        try {
            User user = userRepo.findUserByUsername(username);
            if (user != null) {
                if (updatedUser.getAddress() != null) {
                    user.setAddress(updatedUser.getAddress());
                }
                if (updatedUser.getFullname() != null) {
                    user.setFullname(updatedUser.getFullname());
                }
                if (updatedUser.getPassword() != null) {
                    user.setPassword(updatedUser.getPassword());
                }

                int rowsUpdated = userRepo.updateUser(username, user);
                if (rowsUpdated > 0) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}