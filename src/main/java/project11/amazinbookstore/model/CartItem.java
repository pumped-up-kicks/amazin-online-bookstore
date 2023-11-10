package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Need relationship with registered user
//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "", targetEntity = RegisteredUser.class)
//    private RegisteredUser user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonBackReference
    private Book book;

    private int quantity;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public RegisteredUser getUser() {
//        return user;
//    }
//
//    public void setUser(RegisteredUser user) {
//        this.user = user;
//    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
