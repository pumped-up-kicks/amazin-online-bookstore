package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import java.util.*;

/**
 * Service for interactions with the repository of books.
 * @author Bobby Ngo
 * @version 1.0
 */
@Service
@Slf4j
public class BookService {
    private BookRepository bookRepository;

    /**
     * Creates a BookService object for the given repository of books.
     * @param bookRepository the repository of books.
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a book to the repository.
     * @param book the book to add.
     * @return the added book.
     */
    public Book addBook(Book book) {
        Book existedBook = bookRepository.findByIsbn(book.getIsbn()).orElse(null);
        // isbn is not existed
        if (existedBook == null) {
            Book newBook = bookRepository.save(book);
            log.info("Added new book with title " + newBook.getTitle());
            return newBook;
        }
        log.warn("Cannot create a book with the same isbn");
        return null;
    }

    /**
     * Gets a list of available books from the repository.
     * @return the list of books.
     */
    public List<Book> getAllAvailableBooks(){
        return bookRepository.findAll();
    }

    /**
     * Finds the book in the repository corresponding to an id.
     * @param bookId the book id.
     * @return the corresponding book.
     */
    public Book findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            log.info("Found a book with id " + book.getId());
        }
        return book;
    }

    /**
     * Finds the book in the repository corresponding to a title.
     * @param title the book title.
     * @return the corresponding book.
     */
    public Book findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElse(null);
        if (book != null) {
            log.info("Found a book with title " + book.getTitle());
        }
        return book;
    }

    /**
     * Finds the book in the repository corresponding to the publisher.
     * @param publisher the book title.
     * @return the corresponding book.
     */
    public Book findBookByPublisher(String publisher) {
        Book book = bookRepository.findByPublisher(publisher).orElse(null);
        if (book != null) {
            log.info("Found a book with publisher " + book.getPublisher());
        }
        return book;
    }

    /**
     * Finds the book in the repository corresponding to the publisher.
     * @param isbn the book title.
     * @return the corresponding book.
     */
    public Book findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        if (book != null) {
            log.info("Found a book with isbn " + book.getIsbn());
        }
        return book;
    }

    /**
     * Updates a book in the repository corresponding to an id.
     * @param bookId the book id.
     * @param updatedBook the new book value.
     * @return the updated book.
     */
    @Transactional
    public Book updateBook(Long bookId, Book updatedBook) {
        Book existedBook = bookRepository.findById(bookId).orElse(null);

        if (existedBook != null) {
            String updatedTitle = updatedBook.getTitle();
            String updatedAuthor = updatedBook.getPublisher();
            String updatedGenres = updatedBook.getIsbn();
            String updatedPicture = updatedBook.getPicture();
            int updatedInventoryQuantity = updatedBook.getInventoryQuantity();
            int updatedPrice = updatedBook.getPrice();

            // For update we will check if the updated value is not null, empty and updated value is not the same as old one
            if (updatedTitle != null &&
                    !updatedTitle.isEmpty() &&
                    !Objects.equals(updatedTitle, existedBook.getTitle())) {
                existedBook.setTitle(updatedTitle);
                log.info("Successfully updated title " + updatedTitle);
            }

            if (updatedAuthor != null &&
                    !updatedAuthor.isEmpty() &&
                    !Objects.equals(updatedAuthor, existedBook.getPublisher())) {
                existedBook.setPublisher(updatedAuthor);
                log.info("Successfully updated author " + updatedAuthor);
            }

            if (updatedGenres != null &&
                    !updatedGenres.isEmpty() &&
                    !Objects.equals(updatedGenres, existedBook.getIsbn())) {
                existedBook.setIsbn(updatedGenres);
                log.info("Successfully updated genres " + updatedGenres);
            }

            if (updatedPicture != null &&
                    !updatedPicture.isEmpty() &&
                    !Objects.equals(updatedPicture, existedBook.getPicture())) {
                existedBook.setPicture(updatedPicture);
                log.info("Successfully updated picture " + updatedPicture);
            }

            if (updatedInventoryQuantity > 0 && updatedInventoryQuantity != existedBook.getInventoryQuantity()) {
                existedBook.setInventoryQuantity(updatedInventoryQuantity);
                log.info("Successfully updated inventory quantity " + updatedInventoryQuantity);
            }

            if (updatedPrice > 0 && updatedPrice != existedBook.getInventoryQuantity()) {
                existedBook.setPrice(updatedPrice);
                log.info("Successfully updated price " + updatedPrice);
            }
        }
        return existedBook;
    }

    /**
     * Deletes a book from the database corresponding to an id.
     * @param bookId the id of the book.
     * @return the deleted book
     */
    public Book deleteBook(Long bookId) {
        Book targetBook = bookRepository.findById(bookId).orElse(null);

        if(targetBook == null) {
            log.error("Book with the id " + bookId + " does not exist");
        } else {
            bookRepository.delete(targetBook);
            log.info("Deleted a book with ID " + bookId);
        }
        return targetBook;
    }

    public List<Book> searchBooks(String query) {
        // FIXME: i could search by each field separately and then merge the set, but im lazy
        List<Book> titleQueryResult, isbnQueryResult, publisherQueryResult;
        Set<Book> querySet = new HashSet<>();

        titleQueryResult = bookRepository.findByTitleContainingIgnoreCase(query).orElse(new ArrayList<>());
        isbnQueryResult = bookRepository.findByIsbnContainsIgnoreCase(query).orElse(new ArrayList<>());
        publisherQueryResult = bookRepository.findByPublisherContainsIgnoreCase(query).orElse(new ArrayList<>());

        // merge the 3 sets
        querySet.addAll(titleQueryResult);
        querySet.addAll(isbnQueryResult);
        querySet.addAll(publisherQueryResult);
        log.info("Query: " + query);
        log.info("Found books: " + querySet);
        return querySet.stream().toList();
    }
}
