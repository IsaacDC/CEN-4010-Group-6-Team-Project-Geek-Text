package org.geektext.service;

import org.geektext.model.User;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser(User user) {
        jdbcTemplate.update("INSERT INTO users (id, address, fullname, password, username) VALUES (?,?,?,?,?)",
                user.getId(), user.getAddress(), user.getFullname(), user.getPassword(), user.getUsername());
    }

    public List<User> findAllUsers() {

        return jdbcTemplate.query("SELECT * FROM users", (rs, rosNum) -> new User(rs.getInt("id"),
                rs.getString("address"),
                rs.getString("fullname"),
                rs.getString("password"),
                rs.getString("username")));
    }

    @Override
    public User findUserByUsername(String username) {
        try {
            String str = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new User(rs.getInt("id"),
                    rs.getString("address"),
                    rs.getString("fullname"),
                    rs.getString("password"),
                    rs.getString("username")), new Object[] { username });
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteUserById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Transactional
    @Override
    public int updateUser(String username, User updatedUser) {
        return jdbcTemplate.update("UPDATE user SET address=?, fullname=?, password=? WHERE username=?",
                updatedUser.getAddress(), updatedUser.getFullname(), updatedUser.getPassword(), username);
    }

}
