package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
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
        if (book != null) {
            log.info("Found a book with id " + book.getId());
        }
        return book;
    }

    public Book findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElse(null);
        if (book != null) {
            log.info("Found a book with title " + book.getTitle());
        }
        return book;
    }

    @Transactional
    public void updateBook(Long bookId, Book updatedBook) {
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
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);

        if(!exists) {
            log.error("Book with the id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
        log.info("Deleted a book with ID " + bookId);
    }
}
