package org.geektext.controller;

import java.util.Map;
import java.util.List;

import org.geektext.repository.UserRepository;
import org.geektext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userRepo.insertUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verifyUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            boolean isAuthenticated = userRepo.verifyUser(username, password);

            if (isAuthenticated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userRepo.findAllUsers();

        if (users.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(users);

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userRepo.deleteUserById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username,
            @RequestBody User updatedUser) {

        User user = userRepo.findUserByUsername(username);

        if (user == null) {
            return ResponseEntity.noContent().build();
        }

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
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}