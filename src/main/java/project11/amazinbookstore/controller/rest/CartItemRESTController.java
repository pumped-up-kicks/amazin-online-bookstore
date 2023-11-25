package project11.amazinbookstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        Long bookId = bookRequestDTO.getBookId();
        Long userId = bookRequestDTO.getUserId();
        int quantity = bookRequestDTO.getQuantity();
        return service.addBookToCart(bookId, userId, quantity);
    }
}
