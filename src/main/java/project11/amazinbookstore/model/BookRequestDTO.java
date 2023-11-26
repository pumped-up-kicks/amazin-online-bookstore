package project11.amazinbookstore.model;

import lombok.Getter;

@Getter
public class BookRequestDTO {
    private Long bookId;
    private int quantity;

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
