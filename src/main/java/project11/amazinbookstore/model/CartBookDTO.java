package project11.amazinbookstore.model;

import java.util.ArrayList;
import java.util.List;

public class CartBookDTO extends Book {
    public int quantity;

    public CartBookDTO(CartItem cartItem) {
        super(cartItem.getBook());
        quantity = cartItem.getQuantity();
    }

    public static List<CartBookDTO> getCartBookDTOList(List<CartItem> cartItems) {
        List<CartBookDTO> cartBookDTOS = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartBookDTOS.add(new CartBookDTO(cartItem));
        }
        return cartBookDTOS;
    }
}
