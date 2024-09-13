package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {

   List<User> listUsers();
   void saveUser(User user);
   User getUser(Long id);
   void deleteUser(Long id);


}
