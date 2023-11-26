package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Tracks one individual instance of a user adding a book to their cart.
 * @author Bobby Ngo
 * @version 1.0
 */
@Entity
@Table(name = "cart_items")
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private RegisteredUser customer;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonManagedReference
    private Book book;

    private int quantity;

    public CartItem(RegisteredUser customer, Book book, int quantity) {
        this.customer = customer;
        this.book = book;
        this.quantity = quantity;
    }

    /**
     * Set the id of the cart item.
     * @param id the cart item id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the id of the cart item.
     * @return the cart item id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the customer.
     * @return the customer.
     */
    public RegisteredUser getCustomer() {
        return customer;
    }

    /**
     * Set the customer
     */
    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }

    /**
     * Get the book in the cart.
     * @return the book in the cart.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set the book in the cart.
     * @param book the book in the cart.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the quantity of the book in the cart.
     * @return the quantity of the book in the cart.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the book in the cart.
     * @param quantity the quantity of the book in the cart.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
