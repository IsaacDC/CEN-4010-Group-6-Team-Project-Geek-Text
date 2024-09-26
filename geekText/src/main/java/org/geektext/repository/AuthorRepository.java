package org.geektext.repository;

import org.geektext.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository {

        void addAuthor(Author author);

        List<Author> listAllAuthors();

        int getAuthorIdByName(String firstName, String lastName);

        Author selectAuthorByName(String firstName, String lastName);

        int deleteAuthorById(int id);

        int updateAuthor(int id, Author author);

        Author findAuthorById(int authorId);

}
