package project11.amazinbookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project11.amazinbookstore.controller.UserRegistrationController;
import project11.amazinbookstore.controller.rest.BookStoreRESTController;
import project11.amazinbookstore.controller.rest.CartItemRESTController;
import project11.amazinbookstore.repository.BookRepository;
import project11.amazinbookstore.repository.CartItemRepository;
import project11.amazinbookstore.repository.UserRepository;
import project11.amazinbookstore.services.BookService;
import project11.amazinbookstore.services.CartItemService;
import project11.amazinbookstore.services.UserService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple smoke test for ensuring that all necessary components are
 * created in the context.
 */
@SpringBootTest
public class SmokeTest {
    @Autowired private BookStoreRESTController bookStoreRESTController;
    @Autowired private UserRegistrationController userRegistrationController;
    @Autowired private BookService bookService;
    @Autowired private UserService userService;
    @Autowired private BookRepository bookRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private CartItemService cartItemService;
    @Autowired private CartItemRESTController cartItemRESTController;

    @Test
    void contextLoads() throws Exception {
        Object[] contextComponents = {
                bookStoreRESTController,
                userRegistrationController,
                bookService,
                userService,
                bookRepository,
                userRepository,
                cartItemService,
                cartItemRepository,
                cartItemRESTController
        };

        for (Object o : contextComponents) {
            assertThat(o).isNotNull();
        }
    }
}
