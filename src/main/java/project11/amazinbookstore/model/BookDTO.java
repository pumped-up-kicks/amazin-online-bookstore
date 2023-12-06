package project11.amazinbookstore.model;

public class BookDTO {
    private Long id;
    private String title;
    private String publisher;
    private String isbn;
    private String picture;

    private int inventoryQuantity;

    private int price;

    /**
     * Creates a book DTO with the given title, author, genres, and picture.
     * @param title the book title.
     * @param publisher the book author.
     * @param isbn the genres of the book.
     * @param picture a picture of the book.
     * @param inventoryQuantity the stock of the book in inventory.
     * @param price of the book
     */
    public BookDTO(String title, String publisher, String isbn, String picture, int inventoryQuantity, int price) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.picture = picture;
        this.inventoryQuantity = inventoryQuantity;
        this.price = price;
    }

    /**
     * Creates an empty book DTO.
     */
    public BookDTO() {
        this.title = "";
        this.publisher = "";
        this.isbn = "";
        this.picture = "";
        this.inventoryQuantity = 0;
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
     * @param publisher the book author.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
     * @param isbn the book genres.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
