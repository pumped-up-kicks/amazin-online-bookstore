package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project11.amazinbookstore.services.BookService;
import project11.amazinbookstore.services.CartItemService;

@org.springframework.stereotype.Controller
@Slf4j
public class ShoppingCartController {
    private final CartItemService cartItemService;
    private final BookService bookService;

    @Autowired
    public ShoppingCartController(CartItemService cartItemService, BookService bookService) {
        this.cartItemService = cartItemService;
        this.bookService = bookService;
    }

    @GetMapping("/shoppingcart")
    public String shoppingCartPage(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("Books", cartItemService.findItemsInUserCart(username));

        return "shoppingCart";
    }

    @RequestMapping("/shoppingcart/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        cartItemService.removeBookFromCart(Long.parseLong(bookId), username);
        return "redirect:/shoppingcart";
    }

    @RequestMapping("/shoppingcart/checkout")
    public String checkout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long bookId = cartItemService.checkout(username);

        if (bookId == 0L){
            return "redirect:/?successfulCheckout";
        }
        String bookTitle = bookService.findBookById(bookId).getTitle();
        return "redirect:/shoppingcart?invalidQuantity&book=" + bookTitle;
    }

    @GetMapping("/shoppingcart/cancel")
    public String cancel() {
        return "redirect:/";
    }
}