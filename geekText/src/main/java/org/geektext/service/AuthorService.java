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
public class AuthorService implements AuthorRepository{

    public AuthorService(){}

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addAuthor(Author author){
        jdbcTemplate.update("INSERT INTO authordata (firstname, lastname, biography, publisher, authorid) VALUES (?,?,?,?,?)",
                author.getFirstName(), author.getLastName(), author.getBio(), author.getPublisher(), author.getId());
    }

    public List<Author> listAllAuthors(){
        return jdbcTemplate.query("SELECT * FROM authordata", (rs, rosNum) ->
                new Author(rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("biography"),
                            rs.getString("publisher"),
                            rs.getInt("auhtorid")));
    }

    @Override
    public int getAuthorIdByName(String firstName, String lastName){
        return 0;
    }

    @Override
    public Author selectAuthorByName(String firstName, String lastName){
        try{
            String str = "SELECT * FROM authordata WHERE firstname = ? AND lastname = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) ->
                    new Author(rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("biography"),
                                rs.getString("publisher"),
                                rs.getInt("authorid")), new Object[]{firstName, lastName});
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int deleteAuthorById(int id){
        return jdbcTemplate.update("DELETE FROM authordata WHERE authorId = ? ", id);
    }

    @Transactional
    @Override
    public int updateAuthor(int authorId, Author updatedAuthor){
        return jdbcTemplate.update("UPDATE authordata SET firstname = ?, lastname = ?, biography = ?, publisher = ? WHERE authorid = ?",
               updatedAuthor.getFirstName(), updatedAuthor.getLastName(), updatedAuthor.getBio(), updatedAuthor.getPublisher(), authorId);
    }

    @Override
    public Author findAuthorById(int authorId){
        return null;
    }
}

