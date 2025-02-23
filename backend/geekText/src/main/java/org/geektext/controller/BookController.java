package org.geektext.controller;

import java.util.Collections;
import java.util.List;

import org.geektext.model.Book;
import org.geektext.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepo;

    @PostMapping("/books/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookRepo.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book Loaded to Database");
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable long isbn) {
        Book book = bookRepo.getBookByIsbn(isbn);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Book> books = bookRepo.getAllBooks();

            if (books.isEmpty())
                return ResponseEntity.ok(Collections.emptyList());

            return ResponseEntity.ok(books);

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
