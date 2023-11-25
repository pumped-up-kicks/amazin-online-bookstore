package project11.amazinbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.CartItem;
import project11.amazinbookstore.model.RegisteredUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public Optional<List<CartItem>> findCartItemsByCustomer(RegisteredUser user);
    public Optional<CartItem> findCartItemByCustomerAndBook(RegisteredUser user, Book book);
}
