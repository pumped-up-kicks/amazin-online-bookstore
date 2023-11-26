package project11.amazinbookstore.model;

public class BookDTO {
    private String title;
    private String author;
    private String genres;
    private String picture;

    private int inventoryQuantity;

    /**
     * Creates a book DTO with the given title, author, genres, and picture.
     * @param title the book title.
     * @param author the book author.
     * @param genres the genres of the book.
     * @param picture a picture of the book.
     * @param inventoryQuantity the stock of the book in inventory.
     */
    public BookDTO(String title, String author, String genres, String picture, int inventoryQuantity) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.picture = picture;
        this.inventoryQuantity = inventoryQuantity;
    }

    /**
     * Creates an empty book DTO.
     */
    public BookDTO() {
        this.title = "";
        this.author = "";
        this.genres = "";
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
}
