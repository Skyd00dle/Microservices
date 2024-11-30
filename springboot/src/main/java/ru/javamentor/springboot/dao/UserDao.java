package ru.javamentor.springboot.dao;

import ru.javamentor.springboot.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
}
