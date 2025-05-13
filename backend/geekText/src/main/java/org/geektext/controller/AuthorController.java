package org.geektext.controller;

import org.geektext.model.Author;
import org.geektext.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/author")
@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author createdAuthor = authorRepo.addAuthor(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Author>> showAuthors() {
        List<Author> authors = authorRepo.listAllAuthors();

        if (authors.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(authors);

    }

    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<Author> findAuthorByName(@PathVariable("firstname") String firstname,
            @PathVariable("lastname") String lastname) {
        Author author = authorRepo.findAuthorByName(firstname, lastname);

        if (author != null) {
            return ResponseEntity.ok().body(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") int id) {
        authorRepo.deleteAuthorById(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update/{firstname}/{lastname}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("firstname") String firstname,
            @PathVariable("lastname") String lastname,
            @RequestBody Author updatedAuthor) {

        Author author = authorRepo.findAuthorByName(firstname, lastname);

        if (author == null) {
            return ResponseEntity.noContent().build();
        }
        if (updatedAuthor.getFirstName() != null) {
            author.setFirstName(updatedAuthor.getFirstName());
        }
        if (updatedAuthor.getLastName() != null) {
            author.setLastName(updatedAuthor.getLastName());
        }
        if (updatedAuthor.getBio() != null) {
            author.setBio(updatedAuthor.getBio());
        }

        int id = author.getId();
        int rowsUpdated = authorRepo.updateAuthor(id, author);

        if (rowsUpdated > 0) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

}
