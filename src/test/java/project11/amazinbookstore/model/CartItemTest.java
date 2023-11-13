package project11.amazinbookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
    }

    @Test
    void setId() {
        cartItem.setId(5L);
        assertEquals(5L, cartItem.getId());
    }

    @Test
    void setBook() {
        Book book = new Book();
        cartItem.setBook(book);
        assertEquals(book, cartItem.getBook());
    }

    @Test
    void setQuantity() {
        cartItem.setQuantity(5);
        assertEquals(5, cartItem.getQuantity());
    }
}