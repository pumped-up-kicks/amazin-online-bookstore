package project11.amazinbookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RegisteredUserTest {

    private RegisteredUser registeredUser;
    private RegisteredUser registeredAdmin;

    @BeforeEach
    void setUp() {
        registeredUser = new RegisteredUser("user", "password", Role.USER);
        registeredUser.setId(10L);
        registeredUser.setUsername("user");
        registeredUser.setPassword("password");
        registeredUser.setRole(Role.USER);
        registeredAdmin = new RegisteredUser("admin1", "password", Role.ADMIN);
    }

    @Test
    void testGetAuthorities() {
        assertTrue(registeredAdmin.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
        assertTrue(registeredUser.getAuthorities().contains(new SimpleGrantedAuthority("USER")));
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(registeredUser.isAccountNonExpired());
        assertTrue(registeredAdmin.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(registeredUser.isAccountNonLocked());
        assertTrue(registeredAdmin.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(registeredUser.isCredentialsNonExpired());
        assertTrue(registeredAdmin.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(registeredUser.isEnabled());
        assertTrue(registeredAdmin.isEnabled());
    }

    @Test
    void testEquals() {
        RegisteredUser equalUser = new RegisteredUser("user", "password", Role.USER);
        equalUser.setId(10L);
        equalUser = new RegisteredUser(10L, "user", "password", Role.USER);
        assertEquals(registeredUser, equalUser);
    }

    @Test
    void testHashCode() {
        registeredUser.hashCode();
    }
}