package org.geektext.service;


import org.geektext.model.Author;
import org.geektext.model.Book;
import org.geektext.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

//    public List<Book> getBooksByAuthorId(Long authorId) {
//        return authorRepository.findBooksByAuthorId(authorId);
//    }


}

