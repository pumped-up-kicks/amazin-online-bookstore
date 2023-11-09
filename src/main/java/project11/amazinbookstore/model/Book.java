package project11.amazinbookstore.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Book {

//    @ManyToOne
//    @JoinColumn
//    private ShoppingCart shoppingCart;

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String genres;
    private String picture;
    private int quantity;


    public Book(String title, String author, String genres, String picture, int quantity) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.picture = picture;
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

//    public ShoppingCart getShoppingCart() {
//        return shoppingCart;
//    }
//
//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
