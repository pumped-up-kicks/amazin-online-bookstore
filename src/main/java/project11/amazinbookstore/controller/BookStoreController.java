package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.BookDTO;
import project11.amazinbookstore.services.BookService;
import project11.amazinbookstore.services.UserService;

/**
 * Provides endpoints for typical website functions and interactions with the book database.
 * @author Ethan Leir
 * @version 0.1
 */
@org.springframework.stereotype.Controller
@Slf4j
public class BookStoreController {

    private BookService bookService;

    /**
     * Creates a BookStoreController object corresponding to a given service for interactions
     * with the book database.
     * @param bookService the service for the book database.
     */
    @Autowired
    public BookStoreController(BookService bookService, UserService userService) {
        this.bookService = bookService;
    }

    /**
     * Directs users to the home page.
     * @return the file containing the home page.
     */
    @GetMapping("/")
    public String homepage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("adminInterface", true);
        } else {
            model.addAttribute("adminInterface", false);
        }
        model.addAttribute("book", new BookDTO());
        model.addAttribute("availableBooks", bookService.getAllAvailableBooks());
        return "index";
    }


    /**
     * Gives the service a book to add to its database.
     * @param book the book to add.
     * @return the file containing the page to return to.
     */
    @RequestMapping(value = "/admin/portal/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addBook(BookDTO book) {
        bookService.addBook(new Book(book.getTitle(), book.getPublisher(), book.getIsbn(), book.getPicture(), book.getInventoryQuantity()));
        return "redirect:/";
    }


    /**
     * Deletes a book corresponding to an id from the database.
     * @param id the id of the book.
     * @return the removed book.
     */
    @PostMapping("/admin/portal/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(Long.parseLong(id));
        return "redirect:/";
    }
}
