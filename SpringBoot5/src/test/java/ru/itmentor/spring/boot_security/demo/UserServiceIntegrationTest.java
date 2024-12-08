package ru.itmentor.spring.boot_security.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setEmail("testuser@mail.ru");
        testUser.setPassword("password");
        userService.addUser(testUser);
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        assertNotNull(allUsers);
        assertTrue(allUsers.size() > 0);
    }

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(testUser.getId());
        assertNotNull(user);
        assertEquals("testuser@mail.ru", user.getEmail());
    }

    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setEmail("newuser@mail.ru");
        newUser.setPassword("newpassword");
        userService.addUser(newUser);
        User retrievedUser = userService.getUserByEmail("newuser@mail.ru");
        assertNotNull(retrievedUser);
        assertEquals("newuser@mail.ru", retrievedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        testUser.setEmail("updated@mail.ru");
        userService.updateUser(testUser);
        User updatedUser = userService.getUserById(testUser.getId());
        assertNotNull(updatedUser);
        assertEquals("updated@mail.ru", updatedUser.getEmail());
    }

    @Test
    public void testRemoveUser() {
        userService.removeUser(testUser.getId());
        User deletedUser = userService.getUserById(testUser.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testGetUserByEmail() {
        User user = userService.getUserByEmail("testuser@mail.ru");
        assertNotNull(user);
        assertEquals("testuser@mail.ru", user.getEmail());
    }
}
