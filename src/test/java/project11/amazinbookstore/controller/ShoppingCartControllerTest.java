package project11.amazinbookstore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.services.BookService;
import project11.amazinbookstore.services.CartItemService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartItemService cartItemService;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ShoppingCartController shoppingCartController = new ShoppingCartController(cartItemService, bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }

    @Test
    public void shoppingCartPageTest() throws Exception {
        mockMvc.perform(get("/shoppingcart"))
                .andExpect(status().isOk())
                .andExpect(view().name("shoppingCart"));
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/shoppingcart/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shoppingcart"));
    }

    @Test
    public void checkoutTest() throws Exception {
        when(cartItemService.checkout(anyString())).thenReturn(0L);

        mockMvc.perform(post("/shoppingcart/checkout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?successfulCheckout"));

        Book book = new Book();
        book.setTitle("Test Book");
        when(bookService.findBookById(anyLong())).thenReturn(book);

        mockMvc.perform(post("/shoppingcart/checkout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shoppingcart?invalidQuantity&book=Test+Book"));
    }

    @Test
    public void cancelTest() throws Exception {
        mockMvc.perform(get("/shoppingcart/cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
