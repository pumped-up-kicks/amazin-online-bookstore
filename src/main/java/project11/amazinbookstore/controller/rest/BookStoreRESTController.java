package project11.amazinbookstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project11.amazinbookstore.model.AuthoritiesDTO;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.BookCardDTO;
import project11.amazinbookstore.model.BookDTO;
import project11.amazinbookstore.services.BookService;

import java.util.List;

/**
 * Provides REST endpoints for a service on the database of books.
 * @author Bobby Ngo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/book")
public class BookStoreRESTController {
    private BookService service;

    /**
     * Creates a BookStoreRESTController object for a given service on the database of books.
     * @param service the service on the database of books.
     */
    @Autowired
    public BookStoreRESTController(BookService service) {
        this.service = service;
    }

    /**
     * Gets a list of available books from the service.
     * @return the list of available books.
     */
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return service.getAllAvailableBooks();
    }

    /**
     * Gets the book corresponding to an id from the service.
     * @param id the id of the book.
     * @return the corresponding book.
     */
    @GetMapping("/{id}")
    public Book findBookById(@PathVariable String id) {
        return service.findBookById(Long.parseLong(id));
    }

    // Not confident with this bc not sure we need
    // end point localhost:3000/book?title="naruto"
    /**
     * Gets the book corresponding to a title from the service.
     * @param title the title of the book.
     * @return the corresponding book.
     */
    @GetMapping(params = "title")
    public Book findBookByTitle(@RequestParam String title) {
        return service.findBookByTitle(title);
    }

    @GetMapping(params= "isbn")
    public Book findBookByIsbn(@RequestParam String isbn) {
        return service.findBookByIsbn(isbn);
    }

    @GetMapping(params = "publisher")
    public Book findBookByPublisher(@RequestParam String publisher) {
        return service.findBookByPublisher(publisher);
    }

    @GetMapping(value = "/search", params = "query")
    public ModelAndView searchBooks(@RequestParam String query) {
        ModelAndView modelAndView = new ModelAndView("common/bookCards");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthoritiesDTO authorities = new AuthoritiesDTO(auth);
        modelAndView.addObject("book", new BookDTO());
        modelAndView.addObject("bookCardDTO", new BookCardDTO(auth, BookCardDTO.Context.HOME));
        modelAndView.addObject("authorities", authorities);
        modelAndView.addObject("availableBooks", service.searchBooks(query));
        return modelAndView;
    }

    /**
     * Gives the service a book to add to its database.
     * @param book the book to add.
     * @return the added book.
     */
    @PostMapping("/admin/add")
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    /**
     * Updates the book value corresponding to an id in the database.
     * @param id the id of the book.
     * @param updateBook the updated book.
     * @return the updated book.
     */
    @PutMapping("/admin/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book updateBook) {
        return service.updateBook(Long.parseLong(id), updateBook);
    }

    /**
     * Deletes a book corresponding to an id from the database.
     * @param id the id of the book.
     * @return the removed book.
     */
    @DeleteMapping("/admin/{id}")
    public Book deleteBook(@PathVariable String id) {
        return service.deleteBook(Long.parseLong(id));
    }

}
