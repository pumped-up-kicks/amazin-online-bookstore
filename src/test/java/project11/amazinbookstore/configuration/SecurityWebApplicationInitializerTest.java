package project11.amazinbookstore.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityWebApplicationInitializerTest {
    private SecurityWebApplicationInitializer securityWebApplicationInitializer;

    @BeforeEach
    void setUp() {
        securityWebApplicationInitializer = new SecurityWebApplicationInitializer();
    }

    @Test
    void contextLoads() {

    }
}