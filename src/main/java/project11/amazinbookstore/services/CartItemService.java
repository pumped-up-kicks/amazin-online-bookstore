package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    public List<CartItem> addBookToCart(Long bookId, String userName, int quantity){
        RegisteredUser user = userService.findUserByUserName(userName);
        Book book = bookService.findBookById(bookId);
        List<CartItem> cartItems = repository.findCartItemsByCustomer(user).orElse(null);

        if (quantity <= 0) {
            return repository.findCartItemsByCustomer(user).orElse(null);
        }
        // Case 1: When the cart is empty
        // Case 2: When the cart doesn't have that book
        CartItem item = repository.findCartItemByCustomerAndBook(user, book).orElse(null);
        assert cartItems != null;
        if (cartItems.isEmpty() || item == null) {
            CartItem newItem = new CartItem(user, book, quantity);
            repository.save(newItem);
            log.info("Added book " + book.getTitle() + " for user " + userName);
        } else {
            // Case 3: When book existed
            int newQuantity = item.getQuantity() + quantity;
            item.setQuantity(newQuantity);
            repository.save(item);
            log.info("Added book " + book.getTitle() + " for user " + userName);
        }
        return repository.findCartItemsByCustomer(user).orElse(null);
    }

    public void removeBookFromCart(){

    }

    public List<CartItem> findItemsInUserCart(String username){
        RegisteredUser user = userService.findUserByUserName(username);
        return repository.findCartItemsByCustomer(user).orElse(null);
    }
}
