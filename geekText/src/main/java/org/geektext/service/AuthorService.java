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
        jdbcTemplate.update("INSERT INTO author (id, bio, firstName, lastName, publisher) VALUES (?,?,?,?,?)",
                author.getId(), author.getBio(), author.getFirstName(), author.getLastName(), author.getPublisher());
    }

    public List<Author> listAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM author", (rs, rosNum) -> new Author(rs.getString("id"),
                rs.getString("bio"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getInt("publisher")));
    }

    @Override
    public int getAuthorIdByName(String firstName, String lastName) {
        return 0;
    }

    @Override
    public Author selectAuthorByName(String firstName, String lastName) {
        try {
            String str = "SELECT * FROM author WHERE firstName = ? AND lastName = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(rs.getString("id"),
                    rs.getString("bio"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("publisher")), new Object[] { firstName, lastName });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAuthorById(int id) {
        return jdbcTemplate.update("DELETE FROM author WHERE id = ? ", id);
    }

    @Transactional
    @Override
    public int updateAuthor(int authorId, Author updatedAuthor) {
        return jdbcTemplate.update(
                "UPDATE author SET firstName = ?, lastName = ?, bio = ?, publisher = ? WHERE id = ?",
                updatedAuthor.getFirstName(), updatedAuthor.getLastName(), updatedAuthor.getBio(),
                updatedAuthor.getPublisher(), authorId);
    }

    @Override
    public Author findAuthorById(int authorId) {
        try {
            String str = "SELECT * FROM author WHERE id = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new Author(rs.getString("id"),
                    rs.getString("bio"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("publisher")), new Object[] { authorId });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}