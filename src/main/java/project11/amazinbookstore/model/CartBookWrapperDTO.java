package project11.amazinbookstore.model;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CartBookWrapperDTO extends Book {
    public int quantity;

    public CartBookWrapperDTO(CartItem cartItem) {
        super(cartItem.getBook());
        quantity = cartItem.getQuantity();
        log.info(cartItem.getBook().toString());
    }

    public static List<CartBookWrapperDTO> getCartBookDTOList(List<CartItem> cartItems) {
        List<CartBookWrapperDTO> cartBookWrapperDTOS = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartBookWrapperDTOS.add(new CartBookWrapperDTO(cartItem));
        }

        return cartBookWrapperDTOS;
    }


}
