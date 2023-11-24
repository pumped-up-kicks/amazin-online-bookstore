package project11.amazinbookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService{
    private CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    public void addBookToCart(){

    }

    public void deleteBookFromCart(){

    }

    public List<Book> findAllBookInCart(){
        return null;
    }
}
