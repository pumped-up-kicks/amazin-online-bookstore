package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.repository.CartItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartItemService{
    private UserService userService;
    private BookService bookService;

    private PurchasedItemService purchasedItemService;

    private CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository, UserService userService, BookService bookService, PurchasedItemService purchasedItemService) {
        this.repository = repository;
        this.userService = userService;
        this.bookService = bookService;
        this.purchasedItemService = purchasedItemService;
    }

    public List<CartItem> addBookToCart(Long bookId, String userName, int quantity){
        RegisteredUser user = userService.findUserByUserName(userName);
        Book book = bookService.findBookById(bookId);
        List<CartItem> cartItems = repository.findCartItemsByCustomer(user).orElse(null);
        List<CartItem> returnedCart = null;

        if (quantity <= 0) {
            return repository.findCartItemsByCustomer(user).orElse(null);
        }
        // Case 1: When the cart is empty
        // Case 2: When the cart doesn't have that book
        CartItem item = repository.findCartItemByCustomerAndBook(user, book).orElse(null);
        assert cartItems != null;
        if (cartItems.isEmpty() || item == null) {
            if (quantity <= book.getInventoryQuantity()) {
                CartItem newItem = new CartItem(user, book, quantity);
                repository.save(newItem);
                log.info("Added book " + book.getTitle() + " for user " + userName);
                returnedCart = repository.findCartItemsByCustomer(user).orElse(null);
            } else {
                log.info("Quantity " + quantity + " invalid for book " + book.getTitle() + " with inventory " + book.getInventoryQuantity());
            }
        } else {
            // Case 3: When book existed
            int newQuantity = item.getQuantity() + quantity;
            if (newQuantity <= book.getInventoryQuantity()) {
                item.setQuantity(newQuantity);
                repository.save(item);
                log.info("Added book " + book.getTitle() + " for user " + userName);
                returnedCart = repository.findCartItemsByCustomer(user).orElse(null);
            } else {
                log.info("Quantity " + newQuantity + " invalid for book " + book.getTitle() + " with inventory " + book.getInventoryQuantity());
            }
        }
        return returnedCart;
    }

    @Transactional
    public void removeBookFromCart(Long bookId, String userName){

        Book book = bookService.findBookById(bookId);
        RegisteredUser user = userService.findUserByUserName(userName);

        CartItem bookToBeRemoved = repository.findCartItemByCustomerAndBook(user, book).orElse(null);

        bookToBeRemoved.setBook(null);
        bookToBeRemoved.setCustomer(null);

        repository.delete(bookToBeRemoved);
    }

    @Transactional
    public Long checkout(String userName){
        List<CartItem> cartItems = findItemsInUserCart(userName);
        RegisteredUser user = userService.findUserByUserName(userName);
        for(CartItem item: cartItems){
            Book orderedBook = item.getBook();
            int orderedAmount = item.getQuantity();
            int currentInventoryQuantity = orderedBook.getInventoryQuantity();
            if(currentInventoryQuantity < orderedAmount){
                return orderedBook.getId();
            }
        }

        for(CartItem item: cartItems){
            Book orderedBook = item.getBook();
            int orderedAmount = item.getQuantity();
            int currentInventoryQuantity = orderedBook.getInventoryQuantity();
            orderedBook.setInventoryQuantity(currentInventoryQuantity - orderedAmount);

            purchasedItemService.addPurchasedItemToHistory(user, orderedBook, orderedAmount);
        }

        for(CartItem items: user.getCartItem()){
            items.setCustomer(null);
            items.setBook(null);
        }

        repository.deleteAll(user.getCartItem());

        log.info("Clearing " + userName + "'s Shopping Cart");

        return 0L;
    }

    public List<CartItem> findItemsInUserCart(String username){
        RegisteredUser user = userService.findUserByUserName(username);
        List<CartItem> items = repository.findCartItemsByCustomer(user).orElse(new ArrayList<>());
        log.info(items.toString());
        return items;
    }

    public float getTotalCartPrice(String username) {
        List<CartItem> items = findItemsInUserCart(username);
        float totalCartPrice = 0;
        int quantity, price;

        for (CartItem item : items) {
            quantity = item.getQuantity();
            price = item.getBook().getPrice();
            totalCartPrice += quantity * price;
        }

        return totalCartPrice;
    }

    public boolean isCartEmpty(String username) {
        List<CartItem> items = findItemsInUserCart(username);
        return items.isEmpty();
    }
}
