package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "book")
//    @JoinColumn(name="cart_item_id", referencedColumnName="id")
    //@JoinColumn()
    @JsonBackReference
    private List<CartItem> cartItem = new ArrayList<>();

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "book")
    @JsonBackReference
    private List<PurchasedItem> purchasedItemList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String publisher;
    @Column(unique = true)
    private String isbn;
    private String picture;

    private int price;

    @Column(name = "inventory_quantity")
    private int inventoryQuantity;

    /**
     * Creates a book with the given title, author, genres, picture, and quantity.
     * @param title the book title.
     * @param publisher the book author.
     * @param isbn the genres of the book.
     * @param picture a picture of the book.
     * @param inventoryQuantity the stock of the book in inventory.
     */
    public Book(String title, String publisher, String isbn, String picture, int inventoryQuantity, int price) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.picture = picture;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
    }

    public Book(Book book) {
        this(book.title, book.publisher, book.isbn, book.picture, book.inventoryQuantity, book.price);
        this.id = book.id;
        this.cartItem = book.cartItem;
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

    public List<PurchasedItem> getPurchasedItemList() {
        return purchasedItemList;
    }

    public void setPurchasedItemList(List<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
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
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the author of the book.
     * @param author the book author.
     */
    public void setPublisher(String author) {
        this.publisher = author;
    }

    /**
     * Gets the genres of the book.
     * @return the book genres.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the genres of the book.
     * @param genres the book genres.
     */
    public void setIsbn(String genres) {
        this.isbn = genres;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(publisher, book.publisher) && Objects.equals(isbn, book.isbn) && Objects.equals(picture, book.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publisher, isbn, picture);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", inventoryQuantity=" + inventoryQuantity +
                '}';
    }
}
