package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Tracks database information for a book (including the total stock in inventory).
 * @author Bobby Ngo
 * @version 1.0
 */
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="cart_item_id", referencedColumnName="id")
    @JsonManagedReference
    private List<CartItem> cartItem = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genres;
    private String picture;

    @Column(name = "inventory_quantity")
    private int inventoryQuantity;

    /**
     * Creates a book with the given title, author, genres, picture, and quantity.
     * @param title the book title.
     * @param author the book author.
     * @param genres the genres of the book.
     * @param picture a picture of the book.
     * @param inventoryQuantity the stock of the book in inventory.
     */
    public Book(String title, String author, String genres, String picture, int inventoryQuantity) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.picture = picture;
        this.inventoryQuantity = inventoryQuantity;
    }

    /**
     * Sets the id of the book.
     * @param id the book id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the id of the book.
     * @return the book id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the list of instances of the book in user carts.
     * @return the list of cart instances.
     */
    public List<CartItem> getCartItem() {
        return cartItem;
    }

    /**
     * Sets the list of instances of the book in user carts.
     * @param cartItem the list of cart instances.
     */
    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    /**
     * Gets the title of the book.
     * @return the book title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title the book title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     * @return the book author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author the book author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the genres of the book.
     * @return the book genres.
     */
    public String getGenres() {
        return genres;
    }

    /**
     * Sets the genres of the book.
     * @param genres the book genres.
     */
    public void setGenres(String genres) {
        this.genres = genres;
    }

    /**
     * Gets the picture of the book.
     * @return the book picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the picture of the book.
     * @param picture a book picture.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets the stock of the book in inventory.
     * @return the book quantity.
     */
    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    /**
     * Sets the stock of the book in inventory.
     * @param quantity the book quantity.
     */
    public void setInventoryQuantity(int quantity) {
        this.inventoryQuantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genres, book.genres) && Objects.equals(picture, book.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genres, picture);
    }
}
