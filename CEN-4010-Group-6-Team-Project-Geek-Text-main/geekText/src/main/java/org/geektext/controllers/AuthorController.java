package org.geektext.controller;


import org.geektext.model.Author;
import org.geektext.repository.AuthorRepository;
import org.geektext.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {


    @Autowired
    AuthorService authorService;

    @GetMapping("/loadAuthors")
    public String loadAuthors() { // Load 3 Author objects to database

        authorService.addAuthors();
        return "Authors Loaded to Database";
    }


    @Autowired
    AuthorRepository authorRepo;

    @GetMapping("/showAuthors")
    public List<Author> showAuthors() { // Return all Author objects using REST/GET

        return authorRepo.findAll();

    }

}
