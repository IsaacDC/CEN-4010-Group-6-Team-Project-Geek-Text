package org.geektext.service;

import org.geektext.model.Author;
import org.geektext.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService implements AuthorRepository {

    public AuthorService() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Author addAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id, first_name, last_name, bio, publisher) VALUES (?,?,?,?,?)",
                author.getId(), author.getFirstName(), author.getLastName(), author.getBio(), author.getPublisher());

        return author;
    }

    public List<Author> listAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM authors", (rs, rosNum) -> {
            return new Author(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("bio"),
                    rs.getString("publisher"));
        });
    }

    @Override
    public int getAuthorIdByName(String firstName, String lastName) {
        return 0;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        try {
            String str = "SELECT * FROM authors WHERE first_name = ? AND last_name = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("bio"),
                    rs.getString("publisher")), new Object[] { firstName, lastName });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAuthorById(int id) {
        return jdbcTemplate.update("DELETE FROM authors WHERE id = ? ", id);
    }

    @Transactional
    @Override
    public int updateAuthor(int id, Author updatedAuthor) {
        return jdbcTemplate.update(
                "UPDATE authors SET first_name = ?, last_name = ?, bio = ?, publisher = ? WHERE id = ?",
                updatedAuthor.getFirstName(), updatedAuthor.getLastName(), updatedAuthor.getBio(),
                updatedAuthor.getPublisher(), id);
    }

    @Override
    public Author findAuthorById(int id) {
        try {
            String str = "SELECT * FROM authors WHERE id = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("bio"),
                    rs.getString("publisher")), new Object[] { id });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
