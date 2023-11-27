package project11.amazinbookstore.model;

import java.util.ArrayList;
import java.util.List;

public class CartBookWrapperDTO extends Book {
    public int quantity;

    public CartBookWrapperDTO(CartItem cartItem) {
        super(cartItem.getBook());
        quantity = cartItem.getQuantity();
    }

    public static List<CartBookWrapperDTO> getCartBookDTOList(List<CartItem> cartItems) {
        List<CartBookWrapperDTO> cartBookWrapperDTOS = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartBookWrapperDTOS.add(new CartBookWrapperDTO(cartItem));
        }

        return cartBookWrapperDTOS;
    }
}
