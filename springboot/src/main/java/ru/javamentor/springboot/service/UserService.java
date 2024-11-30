package ru.javamentor.springboot.service;



import ru.javamentor.springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
}
