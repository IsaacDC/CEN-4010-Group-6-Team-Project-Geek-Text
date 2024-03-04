package org.geektext.controller;

import java.util.ArrayList;
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

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userRepo.insertUser(new User(user.getId(), user.getAddress(), user.getFullname(), user.getPassword(), user.getUsername()));
            return new ResponseEntity<>("User was created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        try {

            List<User> users = new ArrayList<>();

            if (username != null) {
                users.addAll(userRepo.selectAllUsers());
                return new ResponseEntity<>(HttpStatus.OK);
            }

            users.addAll(userRepo.selectAllUsers());

            if (users.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        User user = userRepo.selectUserByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        try {
            userRepo.deleteUserById(id);
            return new ResponseEntity<>("User was deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{username}/update")
    public ResponseEntity<String> updateUser(@PathVariable("username") String username,
                                             @RequestBody User updatedUser) {
        try {
            User user = userRepo.selectUserByUsername(username);
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
                    return new ResponseEntity<>("User was updated successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}