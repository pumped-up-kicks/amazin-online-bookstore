package project11.amazinbookstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.BookRequestDTO;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.services.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemRESTController {
    private CartItemService service;

    @Autowired
    public CartItemRESTController(CartItemService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public List<CartItem> addBookToCart(@RequestBody BookRequestDTO bookRequestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long bookId = bookRequestDTO.getBookId();
        int quantity = bookRequestDTO.getQuantity();
        return service.addBookToCart(bookId, auth.getName(), quantity);
    }

    @GetMapping("/viewCart")
    public List<CartItem> getAllItemsInUserCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.findItemsInUserCart(auth.getName());
    }
}
