package org.geektext.repository;

import org.geektext.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

      boolean verifyUser(String username, String password);

      User insertUser(User user);

      List<User> findAllUsers();

      User findUserByUsername(String username);

      int deleteUserById(int id);

      int updateUser(String username, User user);
}