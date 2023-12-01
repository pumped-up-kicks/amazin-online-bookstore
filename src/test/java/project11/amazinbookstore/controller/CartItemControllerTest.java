package project11.amazinbookstore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project11.amazinbookstore.model.BookRequestDTO;
import project11.amazinbookstore.services.CartItemService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CartItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartItemService cartItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CartItemController cartItemController = new CartItemController(cartItemService);
        mockMvc = MockMvcBuilders.standaloneSetup(cartItemController).build();
    }

    @Test
    public void addToCartTest_Success() throws Exception {
        BookRequestDTO requestDTO = new BookRequestDTO();
        requestDTO.setBookId(1L);
        requestDTO.setQuantity(1);

        when(cartItemService.addBookToCart(anyLong(), anyString(), anyInt())).thenReturn(Collections.singletonList(new CartItem()));

        mockMvc.perform(post("/portal/addToCart").flashAttr("bookRequest", requestDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(cartItemService, times(1)).addBookToCart(anyLong(), anyString(), anyInt());
    }

    @Test
    public void addToCartTest_Error() throws Exception {
        BookRequestDTO requestDTO = new BookRequestDTO();
        requestDTO.setBookId(1L);
        requestDTO.setQuantity(1);

        when(cartItemService.addBookToCart(anyLong(), anyString(), anyInt())).thenReturn(null);

        mockMvc.perform(post("/portal/addToCart").flashAttr("bookRequest", requestDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?addToCartError"));

        verify(cartItemService, times(1)).addBookToCart(anyLong(), anyString(), anyInt());
    }
}
