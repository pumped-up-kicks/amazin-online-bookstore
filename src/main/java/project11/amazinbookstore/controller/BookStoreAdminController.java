package project11.amazinbookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.services.BookServices;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookStoreAdminController {
    private BookServices services;

    @Autowired
    public BookStoreAdminController(BookServices services) {
        this.services = services;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return services.getAllAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable String id) {
        return services.findBookById(Long.parseLong(id));
    }

    // Not confident with this bc not sure we need
    // end point localhost:3000/book?title="naruto"
    @GetMapping
    public Book findBookByTitle(@RequestParam String title) {
        return services.findBookByTitle(title);
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        services.addBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable String id, @RequestBody Book updateBook) {
        services.updateBook(Long.parseLong(id), updateBook);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable String id) {
        services.deleteBook(Long.parseLong(id));
    }

}
