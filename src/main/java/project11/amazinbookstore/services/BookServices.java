package project11.amazinbookstore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import java.util.List;
import java.util.Objects;


@Service
public class BookServices {
    private BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookServices.class);

    @Autowired
    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        log.info("Added new book with title " + book.getTitle());
    }

    public List<Book> getAllAvailableBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        log.info("Found a book with title " + book.getTitle());
        return book;
    }

    public Book findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElse(null);
        log.info("Found a book with title " + book.getTitle());
        return book;
    }

    @Transactional
    public void updateBook(Long bookId, Book updatedBook) {
        Book existedBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException(
                "Book with id " + bookId + " does not exists"
        ));
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
        }

        if (updatedAuthor != null &&
                !updatedAuthor.isEmpty() &&
                !Objects.equals(updatedAuthor, existedBook.getAuthor())) {
            existedBook.setAuthor(updatedAuthor);
        }

        if (updatedGenres != null &&
                !updatedGenres.isEmpty() &&
                !Objects.equals(updatedGenres, existedBook.getGenres())) {
            existedBook.setGenres(updatedGenres);
        }

        if (updatedPicture != null &&
                !updatedPicture.isEmpty() &&
                !Objects.equals(updatedPicture, existedBook.getPicture())) {
            existedBook.setPicture(updatedPicture);
        }

        if (updatedInventoryQuantity > 0 && updatedInventoryQuantity != existedBook.getInventoryQuantity()) {
            existedBook.setInventoryQuantity(updatedInventoryQuantity);
        }
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);

        if(!exists) {
            throw new IllegalStateException("Book with the id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
        log.info("Deleted a book with ID " + bookId);
    }
}
