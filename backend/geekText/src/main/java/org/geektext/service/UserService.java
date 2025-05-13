package org.geektext.service;

import org.geektext.model.User;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User insertUser(User user) {
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        jdbcTemplate.update("INSERT INTO users (id, address, fullname, password, username) VALUES (?,?,?,?,?)",
                user.getId(), user.getAddress(), user.getFullname(), user.getPassword(), user.getUsername());

        return user;
    }

    @Override
    public boolean verifyUser(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try {
            String storedPassword = jdbcTemplate.queryForObject(sql, String.class, username);

            return encoder.matches(password, storedPassword);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Authentication Failed" + e);
            return false;
        }

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
            String str = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> new User(
                    rs.getInt("id"),
                    rs.getString("address"),
                    rs.getString("fullname"),
                    rs.getString("password"),
                    rs.getString("username")), username);

    }

    @Override
    public int deleteUserById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Transactional
    @Override
    public int updateUser(String username, User updatedUser) {
        return jdbcTemplate.update("UPDATE users SET address=?, fullname=?, password=? WHERE username=?",
                updatedUser.getAddress(), updatedUser.getFullname(), updatedUser.getPassword(), username);
    }

}
