package org.geektext.repository;

import org.geektext.model.User;

import java.util.List;

public interface UserRepository {

      void insertUser(User user);

      List<User> selectAllUsers();

      int getUserIdByUsername(String username);

      User selectUserByUsername(String username);


      int deleteUserById(int id);

      int updateUser(String username, User user);

      User findUserById(int userID);
}