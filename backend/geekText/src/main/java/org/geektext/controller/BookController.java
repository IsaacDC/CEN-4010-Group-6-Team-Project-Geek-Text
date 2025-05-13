package org.geektext.controller;

import java.util.List;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.geektext.service.AuthorService;
import org.geektext.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        Author author = authorService.findAuthorByName(book.getAuthor().getFirstName(), book.getAuthor().getLastName());

        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        author.addBook(book);
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable long isbn) {
        Book book = bookService.getBookByIsbn(isbn);

        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(books);

    }

}
