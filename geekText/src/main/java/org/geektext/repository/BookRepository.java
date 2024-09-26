
package org.geektext.repository;

import java.util.List;

import org.geektext.model.Author;
import org.geektext.model.Book;

public interface BookRepository {
    void addBook(Book book);

    Book findBookByIsbn(long isbn);

    List<Book> findAllByAuthor(Author author);

}
