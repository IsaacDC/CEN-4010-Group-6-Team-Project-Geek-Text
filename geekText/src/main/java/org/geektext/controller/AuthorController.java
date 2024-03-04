package org.geektext.controller;

import org.geektext.model.Author;
import org.geektext.repository.AuthorRepository;
import org.geektext.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api")
@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @PostMapping("/authors/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        try {
            authorRepo.addAuthor(new Author(author.getFirstName(), author.getLastName(), author.getBio(), author.getPublisher(), author.getId()));
            return new ResponseEntity<>("Author Loaded to Database", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/authors/list")
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




