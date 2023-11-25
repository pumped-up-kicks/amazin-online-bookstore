package project11.amazinbookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.repository.CartItemRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartItemService{
    private UserService userService;
    private BookService bookService;
    private CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository, UserService userService, BookService bookService) {
        this.repository = repository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<CartItem> addBookToCart(Long bookId, Long userId, int quantity){
        RegisteredUser user = userService.findUserById(userId);
        Book book = bookService.findBookById(bookId);
        List<CartItem> cartItems = repository.findCartItemsByCustomer(user).orElse(null);
        // Case 1: When the cart is empty
        // Case 2: When the cart doesn't have that book
        if (cartItems == null) {
            CartItem item = new CartItem(user, book, quantity);
            repository.save(item);
        } else {
            // Case 3: When book existed
            CartItem item = repository.findCartItemByCustomerAndBook(user, book).orElse(null);
            if (item != null) {
                int newQuantity = item.getQuantity() + quantity;
                item.setQuantity(newQuantity);
                repository.save(item);
            }
        }
        return repository.findCartItemsByCustomer(user).orElse(null);
    }

    public void removeBookFromCart(){

    }

    public List<CartItem> findItemsInUserCart(Long userId){
        RegisteredUser user = userService.findUserById(userId);
        return repository.findCartItemsByCustomer(user).orElse(null);
    }
}
