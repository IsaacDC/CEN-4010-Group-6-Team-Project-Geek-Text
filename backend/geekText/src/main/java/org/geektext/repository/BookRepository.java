
package org.geektext.repository;

import java.util.List;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {
    void addBook(Book book);

    List<Book> getAllBooks();

    Book getBookByIsbn(long isbn);

    List<Book> findAllByAuthor(Author author);

}
