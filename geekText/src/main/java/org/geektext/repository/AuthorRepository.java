package org.geektext.repository;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AuthorRepository{

        void addAuthor(Author author);

        List<Author> listAllAuthors();

        int getAuthorIdByName(String firstName, String lastName);

        Author selectAuthorByName(String firstName, String lastName);
        int deleteAuthorById(int id);

        int updateAuthor(int id, Author author);

        Author findAuthorById(int auhtorId);


}


