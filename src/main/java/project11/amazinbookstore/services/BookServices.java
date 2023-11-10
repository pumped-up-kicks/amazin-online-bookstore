package project11.amazinbookstore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.BookRepository;

import java.util.List;


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

    public Book updateBook(Book book) {
        Book existedBook = bookRepository.findById(book.getId()).orElse(null);

        return null;
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
