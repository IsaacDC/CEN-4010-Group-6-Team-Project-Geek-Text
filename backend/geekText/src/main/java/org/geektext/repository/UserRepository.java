package org.geektext.repository;

import org.geektext.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
      void insertUser(User user);

      List<User> findAllUsers();

      User findUserByUsername(String username);

      int deleteUserById(int id);

      int updateUser(String username, User user);
}