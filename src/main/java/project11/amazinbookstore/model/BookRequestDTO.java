package project11.amazinbookstore.model;

import lombok.Getter;

@Getter
public class BookRequestDTO {
    private Long bookId;
    private Long userId;
    private int quantity;

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
