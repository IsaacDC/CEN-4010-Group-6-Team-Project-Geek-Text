package org.geektext.repository;

import org.geektext.model.Author;

import java.util.List;

public interface AuthorRepository {

        void addAuthor(Author author);

        List<Author> listAllAuthors();

        int getAuthorIdByName(String firstName, String lastName);

        Author selectAuthorByName(String firstName, String lastName);

        int deleteAuthorById(int id);

        int updateAuthor(int id, Author author);

        Author findAuthorById(int authorId);

}
