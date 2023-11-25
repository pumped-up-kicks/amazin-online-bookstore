package project11.amazinbookstore.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.services.BookService;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest(BookStoreRESTController.class)
//@WithMockUser(roles = {"USER"})
class BookStoreRESTControllerMockTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectWriter ow = new ObjectMapper().writer();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    /**
     * Validates
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = {"USER"})
    void testGetAllBooksEmpty() throws Exception {
        when(bookService.getAllAvailableBooks()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/book/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testFindBookByIdExists() throws Exception {
        Long id = 1L;
        Book mockOutput = new Book("title", "author", "genres", "picture", 5);

        when(bookService.findBookById(id)).thenReturn(mockOutput);
        mockMvc.perform(get("/api/book/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testFindBookByTitle() throws Exception {
        String title = "a peculiar title";
        Book mockOutput = new Book(title, "author", "genres", "picture", 5);

        when(bookService.findBookByTitle(title)).thenReturn(mockOutput);
        mockMvc.perform(get("/api/book")
                        .param("title", title))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testAddBookAsUser() throws Exception {
        Book newBook = new Book("title", "author", "genres", "picture", 5);
        newBook.setId(1L);

        String requestJson = ow.writeValueAsString(newBook);

        when(bookService.addBook(newBook)).thenReturn(newBook);
        mockMvc.perform(post("/api/book/admin/add")
                        .content(requestJson)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    // FIXME: doesn't work, give 415 response, Error message = Content-Type 'application/json' is not supported.
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void testAddBookAsAdmin() throws Exception {
//        Book newBook = new Book("title", "author", "genres", "picture", 5);
//        newBook.setId(1L);
//
//        String requestJson = ow.writeValueAsString(newBook);
//
//        when(bookService.addBook(newBook)).thenReturn(newBook);
//        mockMvc.perform(post("/api/book/admin/add")
//                        .content(requestJson)
//                        .with(csrf())
//                        .contentType("application/json"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(requestJson));
//    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testUpdateBookAsUser() throws Exception {
        Long id = 1L;
        Book newBook = new Book("title", "author", "genres", "picture", 5);
        String requestJson = ow.writeValueAsString(newBook);

        when(bookService.updateBook(id, newBook)).thenReturn(newBook);
        mockMvc.perform(put("/api/book/admin/1")
                        .content(requestJson)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

// FIXME: doesn't work, give 415 response, Error message = Content-Type 'application/json' is not supported.

//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void testUpdateBookAsAdmin() throws Exception {
//        Long id = 1L;
//        Book newBook = new Book("title", "author", "genres", "picture", 5);
//        String requestJson = ow.writeValueAsString(newBook);
//
//        when(bookService.updateBook(id, newBook)).thenReturn(newBook);
//        mockMvc.perform(put("/api/book/admin/1")
//                        .content(requestJson)
//                        .contentType("application/json")
//                        .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testDeleteBookAsUser() throws Exception {
        Book newBook = new Book("title", "author", "genres", "picture", 5);
        when(bookService.deleteBook(1L)).thenReturn(newBook);
        mockMvc.perform(delete("/api/book/admin/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testDeleteBookAsAdmin() throws Exception {
        Book newBook = new Book("title", "author", "genres", "picture", 5);
        when(bookService.deleteBook(1L)).thenReturn(newBook);
        mockMvc.perform(delete("/api/book/admin/1")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}