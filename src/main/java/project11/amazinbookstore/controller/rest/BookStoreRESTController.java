package project11.amazinbookstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookStoreRESTController {
    private BookService service;

    @Autowired
    public BookStoreRESTController(BookService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return service.getAllAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable String id) {
        return service.findBookById(Long.parseLong(id));
    }

    // Not confident with this bc not sure we need
    // end point localhost:3000/book?title="naruto"
    @GetMapping
    public Book findBookByTitle(@RequestParam String title) {
        return service.findBookByTitle(title);
    }

    @PostMapping("/admin/add")
    public void addBook(@RequestBody Book book) {
        service.addBook(book);
    }

    @PutMapping("/admin/{id}")
    public void updateBook(@PathVariable String id, @RequestBody Book updateBook) {
        service.updateBook(Long.parseLong(id), updateBook);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteBook(@PathVariable String id) {
        service.deleteBook(Long.parseLong(id));
    }

}
