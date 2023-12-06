package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project11.amazinbookstore.model.BookRequestDTO;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.services.CartItemService;

import java.util.List;

/**
 * Provides endpoints for typical website functions and interactions with the cart item database.
 * @author Ethan Leir
 * @version 0.1
 */
@org.springframework.stereotype.Controller
@Slf4j
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService service) {
        cartItemService = service;
    }
//    @PostMapping(value = "/portal/addToCart", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping("/portal/addToCart")
    public String addToCart(@ModelAttribute("bookRequest") BookRequestDTO requestDTO) {
        List<CartItem> result;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result = cartItemService.addBookToCart(requestDTO.getBookId(), auth.getName(), requestDTO.getQuantity());
        if (result != null) {
            log.info(result.toString());
            return "redirect:/";
        } else {
            return "redirect:/?addToCartError";
        }
    }

    @PostMapping("/portal/rec/addToCart")
    public String addToRecCart(@ModelAttribute("bookRequest") BookRequestDTO requestDTO) {
        List<CartItem> result;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result = cartItemService.addBookToCart(requestDTO.getBookId(), auth.getName(), requestDTO.getQuantity());
        if (result != null) {
            log.info(result.toString());
            return "redirect:/recommendations";
        } else {
            return "redirect:/recommendations?addToCartError";
        }
    }
}
