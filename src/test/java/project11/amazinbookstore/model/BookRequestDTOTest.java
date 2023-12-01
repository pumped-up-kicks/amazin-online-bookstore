package project11.amazinbookstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookRequestDTOTest {

    @Test
    public void testBookId() {
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        Long bookId = 1L;
        bookRequestDTO.setBookId(bookId);
        assertEquals(bookId, bookRequestDTO.getBookId());
    }

    @Test
    public void testQuantity() {
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        int quantity = 1;
        bookRequestDTO.setQuantity(quantity);
        assertEquals(quantity, bookRequestDTO.getQuantity());
    }
}
