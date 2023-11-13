package project11.amazinbookstore.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project11.amazinbookstore.model.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddNewUser() {
        assertTrue(userService.addNewUser("user1", "password1", Role.USER));
        assertFalse(userService.addNewUser("user1", "password1", Role.USER));
    }
}