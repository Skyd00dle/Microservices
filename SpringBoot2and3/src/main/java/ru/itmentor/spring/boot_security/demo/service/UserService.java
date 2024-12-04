package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserByEmail(String email);
}
