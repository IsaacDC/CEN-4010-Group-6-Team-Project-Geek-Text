package org.geektext.api;

import java.util.ArrayList;
import java.util.List;

import org.geektext.dao.UserDao;
import org.geektext.dao.UserRepository;
import org.geektext.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserDao userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            userRepo.insertUser(new User(user.getId(), user.getAddress(), user.getFullname(), user.getPassword(), user.getUsername()));
            return new ResponseEntity<>("User was created successfully", HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        try{

            List<User> users = new ArrayList<>();

            if (username != null) {
                users.addAll(userRepo.selectAllUsers());
                return new ResponseEntity<>(HttpStatus.OK);
            }

            users.addAll(userRepo.selectAllUsers());

            if (users.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

           return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

    @GetMapping("/{username}")
   public ResponseEntity getUserByUsername(@PathVariable("username") String username){
        User user = userRepo.selectUserByUsername(username);

        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    }
