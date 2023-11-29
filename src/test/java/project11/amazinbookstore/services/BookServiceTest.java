package project11.amazinbookstore.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repository;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book("title", "author", "isbn1", "picture", 5, 12);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
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
        Book newBook = new Book(title, "author", "isbn2", "picture", 5, 12);
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
        String newIsbn = "a new isbn";
        String newPicture = "a new picture";
        int newQuantity = 10;

        service.addBook(testBook);

        testBook.setPublisher(newAuthor);
        testBook.setTitle(newTitle);
        testBook.setIsbn(newIsbn);
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