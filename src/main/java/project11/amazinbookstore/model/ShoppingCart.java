package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import project11.amazinbookstore.user.RegisteredUser;

import java.util.List;

@Entity
@NoArgsConstructor
public class ShoppingCart {
    private Long id;

    // Need relationship with registered user
//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "", targetEntity = RegisteredUser.class)
//    private RegisteredUser user;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart", targetEntity = Book.class)
//    @JsonManagedReference
//    private List<Book> book;

    private int quantity;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
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

//    public List<Book> getBook() {
//        return book;
//    }
//
//    public void setBook(List<Book> book) {
//        this.book = book;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
