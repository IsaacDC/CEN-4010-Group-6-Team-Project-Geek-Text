package org.geektext.controller;

import org.geektext.model.Author;
import org.geektext.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @PostMapping("/authors/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        try {
            authorRepo.addAuthor(new Author(author.getFirstName(), author.getLastName(), author.getBio(),
                    author.getPublisher(), author.getId()));
            return new ResponseEntity<>("Author Loaded to Database", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authors/list")
    public ResponseEntity<List<Author>> showAuthors(@RequestParam(required = false) String lastName) {
        try {
            List<Author> authors = new ArrayList<>();

            if (lastName != null) {
                authors.addAll(authorRepo.listAllAuthors());
                return new ResponseEntity<>(HttpStatus.OK);
            }

            authors.addAll(authorRepo.listAllAuthors());

            if (authors.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(authors, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<Author> selectAuthorByName(@PathVariable("lastname") String firstname, String lastname) {
        Author author = authorRepo.selectAuthorByName(firstname, lastname);

        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {
        try {
            authorRepo.deleteAuthorById(id);
            return new ResponseEntity<>("Author was deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{lastname}/update")
    public ResponseEntity<String> updateUser(@PathVariable String lastname, String firstname,
            @RequestBody Author updatedAuthor) {
        try {
            Author author = authorRepo.selectAuthorByName(firstname, lastname);
            if (author != null) {
                if (updatedAuthor.getFirstName() != null) {
                    author.setFirstName(updatedAuthor.getFirstName());
                }
                if (updatedAuthor.getFirstName() != null) {

                }
                int id = 0;
                int rowsUpdated = authorRepo.updateAuthor(id, author);
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
