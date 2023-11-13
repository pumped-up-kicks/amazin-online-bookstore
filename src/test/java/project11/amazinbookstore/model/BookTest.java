package project11.amazinbookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("title", "author", "genre", "picture", 5);
    }

    @Test
    void setId() {
        book.setId(5L);
        assertEquals(5L, book.getId());
    }

    @Test
    void setCartItem() {
        book.setCartItem(new ArrayList<>());
        assertEquals(new ArrayList<>(), book.getCartItem());
    }

    @Test
    void setTitle() {
        book.setTitle("new title");
        assertEquals("new title", book.getTitle());
    }

    @Test
    void setAuthor() {
        book.setAuthor("new author");
        assertEquals("new author", book.getAuthor());
    }

    @Test
    void setGenres() {
        book.setGenres("new genres");
        assertEquals("new genres", book.getGenres());
    }

    @Test
    void setPicture() {
        book.setPicture("new picture");
        assertEquals("new picture", book.getPicture());
    }

    @Test
    void setInventoryQuantity() {
        book.setInventoryQuantity(10);
        assertEquals(10, book.getInventoryQuantity());
    }

    @Test
    void testEquals() {
        Book copyBook = new Book("title", "author", "genre", "picture", 5);
        assertEquals(copyBook, book);

        copyBook = new Book();
        assertNotEquals(copyBook, book);
    }

    @Test
    void testHashCode() {
        book.hashCode();
    }
}