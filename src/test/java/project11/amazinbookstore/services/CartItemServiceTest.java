package project11.amazinbookstore.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.repository.CartItemRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartItemServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Mock
    private CartItemRepository cartItemRepository;

    private CartItemService cartItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cartItemService = new CartItemService(userService, bookService, cartItemRepository);
    }

    @Test
    public void addBookToCart_EmptyCart() {
        RegisteredUser user = new RegisteredUser();
        Book book = new Book();
        when(userService.findUserByUserName(anyString())).thenReturn(user);
        when(bookService.findBookById(anyLong())).thenReturn(book);
        when(cartItemRepository.findCartItemsByCustomer(user)).thenReturn(Collections.emptyList());

        List<CartItem> result = cartItemService.addBookToCart(1L, "username", 1);

        assertEquals(1, result.size());
        assertEquals(book, result.get(0).getBook());
    }

    @Test
    public void addBookToCart_ExistingBook() {
        RegisteredUser user = new RegisteredUser();
        Book book = new Book();
        CartItem cartItem = new CartItem(user, book, 1);
        when(userService.findUserByUserName(anyString())).thenReturn(user);
        when(bookService.findBookById(anyLong())).thenReturn(book);
        when(cartItemRepository.findCartItemsByCustomer(user)).thenReturn(Collections.singletonList(cartItem));

        List<CartItem> result = cartItemService.addBookToCart(1L, "username", 1);

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getQuantity());
    }

    @Test
    public void addBookToCart_InvalidQuantity() {
        List<CartItem> result = cartItemService.addBookToCart(1L, "username", 0);

        assertNull(result);

        result = cartItemService.addBookToCart(1L, "username", -1);

        assertNull(result);
    }
}
