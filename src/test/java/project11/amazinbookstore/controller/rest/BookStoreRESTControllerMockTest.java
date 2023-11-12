//package project11.amazinbookstore.controller.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import project11.amazinbookstore.model.Book;
//import project11.amazinbookstore.services.BookService;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(BookStoreRESTController.class)
////@WithMockUser(roles = {"USER"})
//class BookStoreRESTControllerMockTest {
//
//    @Autowired
//    WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookService bookService;
//
//    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();
//    }
//
//    /**
//     * Validates
//     * @throws Exception
//     */
//    @Test
//    void testGetAllBooksEmpty() throws Exception {
//        when(bookService.getAllAvailableBooks()).thenReturn(new ArrayList<>());
//        mockMvc.perform(get("/api/book/all"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("[]"));
//    }
//
//    @Test
//    @WithMockUser(roles = {"USER"})
//    void testAddBookAsUser() throws Exception {
//        Book newBook = new Book("title", "author", "genres", "picture", 5);
//        newBook.setId(1L);
//
//        String requestJson = ow.writeValueAsString(newBook);
//
//        when(bookService.addBook(newBook)).thenReturn(newBook);
//        mockMvc.perform(post("/api/book/admin/add")
//                        .content(requestJson)
//                        .contentType("application/json")
//                        .with(csrf()))
//                .andDo(print())
//                .andExpect(content().string(requestJson));
//                //.andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser("admin")
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
//}