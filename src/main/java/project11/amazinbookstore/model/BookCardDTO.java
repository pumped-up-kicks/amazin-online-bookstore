package project11.amazinbookstore.model;

import org.springframework.security.core.Authentication;

public class BookCardDTO {
    public AuthoritiesDTO authorities;
    public BookRequestDTO bookRequest;
    public Context context;

    public enum Context {
        HOME,
        SHOPPING_CART
    }

    public BookCardDTO(Authentication auth, Context ctx) {
        this.authorities = new AuthoritiesDTO(auth);
        this.bookRequest = new BookRequestDTO();
        this.context = ctx;
    }

    public boolean isHomeContext() {
        return context == Context.HOME;
    }

    public boolean isShoppingCartContext() {
        return context == Context.SHOPPING_CART;
    }
}
