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

    public void addAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author (authorId, first_name, last_name, bio, publisher) VALUES (?,?,?,?,?)",
                author.getId(), author.getBio(), author.getFirstName(), author.getLastName(), author.getPublisher());
    }

    public List<Author> listAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM authors", (rs, rosNum) -> new Author(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("bio"),
                rs.getString("publisher")));
    }

    @Override
    public int getAuthorIdByName(String firstName, String lastName) {
        return 0;
    }

    @Override
    public Author selectAuthorByName(String firstName, String lastName) {
        try {
            String str = "SELECT * FROM authors WHERE first_name = ? AND last_name = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(
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
        return jdbcTemplate.update("DELETE FROM authors WHERE authorId = ? ", id);
    }

    @Transactional
    @Override
    public int updateAuthor(int authorId, Author updatedAuthor) {
        return jdbcTemplate.update(
                "UPDATE author SET first_name = ?, last_name = ?, bio = ?, publisher = ? WHERE id = ?",
                updatedAuthor.getFirstName(), updatedAuthor.getLastName(), updatedAuthor.getBio(),
                updatedAuthor.getPublisher(), authorId);
    }

    @Override
    public Author findAuthorById(int authorId) {
        try {
            String str = "SELECT * FROM authors WHERE authorId = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("bio"),
                    rs.getString("publisher")), new Object[] { authorId });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
