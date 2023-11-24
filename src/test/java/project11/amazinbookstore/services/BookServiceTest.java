package project11.amazinbookstore.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project11.amazinbookstore.model.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService service;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book("title", "author", "genres", "picture", 5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddBook() {
        // verify add book adds book
        // note: the service returns the same object reference
        service.addBook(testBook);
        assertEquals(testBook, service.findBookById(testBook.getId()));
    }

    @Test
    void testFindBookByIdExists() {
        // its not any different from the last test :/
        service.addBook(testBook);
        assertEquals(testBook, service.findBookById(testBook.getId()));
    }

    @Test
    void testFindBookByIdDoesNotExist() {
        assertNull(service.findBookById(999L));
    }

    @Test
    void testFindBookByTitleExists() {
        // FIXME: titles are not unique. There could be multiple books with the same title
        String title = "a peculiar title";
        Book newBook = new Book(title, "author", "genres", "picture", 5);
        service.addBook(newBook);
        assertEquals(newBook, service.findBookByTitle(title));
    }

    @Test
    void testFindBookByTitleDoesNotExist() {
        String title = "another peculiar title";
        assertNull(service.findBookByTitle(title));
    }

    @Test
    void testUpdateBookExists() {
        String newTitle = "a new title";
        String newAuthor = "a new author";
        String newGenre = "a new genre";
        String newPicture = "a new picture";
        int newQuantity = 10;

        service.addBook(testBook);

        testBook.setPublisher(newAuthor);
        testBook.setTitle(newTitle);
        testBook.setIsbn(newGenre);
        testBook.setPicture(newPicture);
        testBook.setInventoryQuantity(newQuantity);

        service.updateBook(testBook.getId(), testBook);

        assertEquals(testBook, service.findBookById(testBook.getId()));
    }

    @Test
    void testGetAllAvailableBooks() {
        assertNotNull(service.getAllAvailableBooks());
    }

    @Test
    void testDeleteBookExists() {
        service.addBook(testBook);
        assertEquals(testBook, service.deleteBook(testBook.getId()));
    }

    @Test
    void testDeleteBookDoesNotExist() {
        testBook.setId(999L);
        assertNull(service.deleteBook(testBook.getId()));
    }
}