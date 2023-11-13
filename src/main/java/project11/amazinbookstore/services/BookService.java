package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import java.util.List;
import java.util.Objects;

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
        Book newBook = bookRepository.save(book);
        log.info("Added new book with title " + newBook.getTitle());
        return newBook;
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
            String updatedAuthor = updatedBook.getAuthor();
            String updatedGenres = updatedBook.getGenres();
            String updatedPicture = updatedBook.getPicture();
            int updatedInventoryQuantity = updatedBook.getInventoryQuantity();

            // For update we will check if the updated value is not null, empty and updated value is not the same as old one
            if (updatedTitle != null &&
                    !updatedTitle.isEmpty() &&
                    !Objects.equals(updatedTitle, existedBook.getTitle())) {
                existedBook.setTitle(updatedTitle);
                log.info("Successfully updated title " + updatedTitle);
            }

            if (updatedAuthor != null &&
                    !updatedAuthor.isEmpty() &&
                    !Objects.equals(updatedAuthor, existedBook.getAuthor())) {
                existedBook.setAuthor(updatedAuthor);
                log.info("Successfully updated author " + updatedAuthor);
            }

            if (updatedGenres != null &&
                    !updatedGenres.isEmpty() &&
                    !Objects.equals(updatedGenres, existedBook.getGenres())) {
                existedBook.setGenres(updatedGenres);
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
}
