package org.geektext.service;

import org.geektext.model.User;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserService implements UserRepository {
    public UserService() {}


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertUser(User user){
        jdbcTemplate.update("INSERT INTO users (userID, address, fullname, password, username) VALUES (?,?,?,?,?)",
                user.getId(), user.getAddress(), user.getFullname(), user.getPassword(), user.getUsername());
    }
    public List<User> selectAllUsers(){

        return jdbcTemplate.query("SELECT * FROM users",(rs, rosNum) ->
                new User(rs.getInt("userID"),
                        rs.getString("address"),
                        rs.getString("fullname"),
                        rs.getString("password"),
                        rs.getString("username")));
    }

    @Override
    public int getUserIdByUsername(String username) {
        return 0;
    }

    @Override
    public User selectUserByUsername(String username) {
        try{
            String str = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(str, new Object[]{username},(rs, rosNum) ->
                    new User(rs.getInt("userID"),
                            rs.getString("address"),
                            rs.getString("fullname"),
                            rs.getString("password"),
                            rs.getString("username")));
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteUserById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE userID=?", id);
    }


    @Transactional
    @Override
    public int updateUser(String username, User updatedUser) {
        return jdbcTemplate.update("UPDATE users SET address=?, fullname=?, password=? WHERE username=?",
                updatedUser.getAddress(), updatedUser.getFullname(), updatedUser.getPassword(), username);
    }

    @Override
    public User findUserById(int userID) {
        return null;
    }
}
