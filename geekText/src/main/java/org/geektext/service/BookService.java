package org.geektext.service;


import org.geektext.model.Book;
import org.geektext.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(int isbn) {
        return bookRepository.findByIsbn(isbn);
    }
//
//    public List<Book> getAuthorsBook(long authorId){
//        return bookRepository.getBookByAuthor(authorId);
//    }



}


