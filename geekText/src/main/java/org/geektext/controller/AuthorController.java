package org.geektext.controller;

import org.geektext.model.Author;
import org.geektext.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/authors/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        try {
            authorService.addAuthor(author);
            return new ResponseEntity<>("Author Loaded to Database", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        @GetMapping("/authors/show")
    public ResponseEntity<List<Author>> showAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }



//    @GetMapping("/authors/{authorId}/books")
//    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("authorId") Long authorId) {
//        List<Book> books = authorService.getBooksByAuthorId(authorId);
//        if (books.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

}




