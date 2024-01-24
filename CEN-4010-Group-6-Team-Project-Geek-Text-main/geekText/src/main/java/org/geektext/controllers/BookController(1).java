package org.geektext.controller;


import org.geektext.model.Book;
import org.geektext.repository.BookRepository;
import org.geektext.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
        // code to handle different endpoints

    @Autowired
    BookService bookServiceTest;

    @GetMapping("/loadBooks")
    public String loadBooks() { // Load 3 book objects to database

        bookServiceTest.addBooks();
        return "Books Loaded to Database";

    }


    @Autowired
    private BookRepository testRepository;

    @GetMapping("/showBooks")
    public List<Book> showBooks() {  // Return all book objects using REST/GET

        return testRepository.findAll();

    }

}
