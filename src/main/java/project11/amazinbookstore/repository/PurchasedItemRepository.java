package project11.amazinbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project11.amazinbookstore.model.PurchasedItem;
import project11.amazinbookstore.model.RegisteredUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Long> {
    Optional<List<PurchasedItem>> findPurchasedItemsByCustomer(RegisteredUser user);
}
