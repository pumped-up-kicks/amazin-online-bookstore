package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@NoArgsConstructor
public class PurchasedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonManagedReference
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private RegisteredUser customer;

    private int quantity;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private Date createdAt;

    public PurchasedItem(Book book, RegisteredUser customer, int quantity, Date createdAt) {
        this.book = book;
        this.customer = customer;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public RegisteredUser getCustomer() {
        return customer;
    }

    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
