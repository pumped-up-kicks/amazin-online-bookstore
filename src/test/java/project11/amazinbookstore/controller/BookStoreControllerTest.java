package project11.amazinbookstore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.services.BookService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookStoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BookStoreController bookStoreController = new BookStoreController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
    }

    @Test
    public void homepageTest() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getAuthorities()).thenReturn(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("adminInterface", true))
                .andExpect(view().name("index"));

        when(auth.getAuthorities()).thenReturn(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("adminInterface", false))
                .andExpect(view().name("index"));
    }

    @Test
    public void addBookTest() throws Exception {
        Book book = new Book();
        book.setTitle("Test Book");

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/addBook").flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).addBook(any(Book.class));
    }
}
