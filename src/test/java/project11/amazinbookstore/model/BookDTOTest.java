package project11.amazinbookstore.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookDTOTest {

    @Test
    public void testTitle() {
        BookDTO bookDTO = new BookDTO();
        String title = "Test Title";
        bookDTO.setTitle(title);
        assertEquals(title, bookDTO.getTitle());
    }

    @Test
    public void testPublisher() {
        BookDTO bookDTO = new BookDTO();
        String publisher = "Test Publisher";
        bookDTO.setPublisher(publisher);
        assertEquals(publisher, bookDTO.getPublisher());
    }

    @Test
    public void testIsbn() {
        BookDTO bookDTO = new BookDTO();
        String isbn = "Test ISBN";
        bookDTO.setIsbn(isbn);
        assertEquals(isbn, bookDTO.getIsbn());
    }

    @Test
    public void testPicture() {
        BookDTO bookDTO = new BookDTO();
        String picture = "Test Picture";
        bookDTO.setPicture(picture);
        assertEquals(picture, bookDTO.getPicture());
    }

    @Test
    public void testInventoryQuantity() {
        BookDTO bookDTO = new BookDTO();
        int inventoryQuantity = 10;
        bookDTO.setInventoryQuantity(inventoryQuantity);
        assertEquals(inventoryQuantity, bookDTO.getInventoryQuantity());
    }

    @Test
    public void testPrice() {
        BookDTO bookDTO = new BookDTO();
        int price = 100;
        bookDTO.setPrice(price);
        assertEquals(price, bookDTO.getPrice());
    }

    @Test
    public void testConstructor() {
        String title = "Test Title";
        String publisher = "Test Publisher";
        String isbn = "Test ISBN";
        String picture = "Test Picture";
        int inventoryQuantity = 10;
        int price = 100;

        BookDTO bookDTO = new BookDTO(title, publisher, isbn, picture, inventoryQuantity, price);

        assertEquals(title, bookDTO.getTitle());
        assertEquals(publisher, bookDTO.getPublisher());
        assertEquals(isbn, bookDTO.getIsbn());
        assertEquals(picture, bookDTO.getPicture());
        assertEquals(inventoryQuantity, bookDTO.getInventoryQuantity());
        assertEquals(price, bookDTO.getPrice());
    }
}
