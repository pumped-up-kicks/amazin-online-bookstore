package project11.amazinbookstore.model;

import org.springframework.security.core.Authentication;

/**
 * Transfer object describing the handling of book card code fragments.
 * @author Zakaria Ismail
 * @version 1.1
 */
public class BookCardDTO {
    public AuthoritiesDTO authorities;
    public BookRequestDTO bookRequest;
    public Context context;

    public enum Context {
        HOME,
        SHOPPING_CART,
        PURCHASE_HISTORY
    }

    /**
     * Creates the object.
     * @param auth the authentication system (for user lookup)
     * @param ctx the context
     */
    public BookCardDTO(Authentication auth, Context ctx) {
        this.authorities = new AuthoritiesDTO(auth);
        this.bookRequest = new BookRequestDTO();
        this.context = ctx;
    }

    /**
     * Check if it is the home context.
     * @return true if it is the home context.
     */
    public boolean isHomeContext() {
        return context == Context.HOME;
    }

    /**
     * Check if it is the shopping cart context.
     * @return true if it is the shopping cart context.
     */
    public boolean isShoppingCartContext() {
        return context == Context.SHOPPING_CART;
    }

    /**
     * Check if it is the purchase history context.
     * @return true if it is the purchase history context.
     */
    public boolean isPurchaseHistoryContext() {
        return context == Context.PURCHASE_HISTORY;
    }
}
