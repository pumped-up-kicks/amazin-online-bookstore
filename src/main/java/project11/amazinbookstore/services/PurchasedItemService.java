package project11.amazinbookstore.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.PurchasedItem;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.repository.PurchasedItemRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PurchasedItemService {
    private UserService userService;
    private PurchasedItemRepository purchasedItemRepository;

    @Autowired
    public PurchasedItemService(UserService userService, PurchasedItemRepository purchasedItemRepository) {
        this.userService = userService;
        this.purchasedItemRepository = purchasedItemRepository;
    }

    public void addPurchasedItemToHistory(RegisteredUser user, Book book, int quantity) {
        //List<PurchasedItem> currPurchaseItems = purchasedItemRepository.findPurchasedItemsByCustomer(user).orElse(null);
        PurchasedItem newPurchasedItem = new PurchasedItem(book, user, quantity, new Date());
        purchasedItemRepository.save(newPurchasedItem);
        log.info("Successfully added item " + book.getTitle() + " to user " + user.getUsername() +" history");
    }

    public List<PurchasedItem> findAllPurchasedItems(String name) {
        RegisteredUser user = userService.findUserByUserName(name);
        return purchasedItemRepository.findPurchasedItemsByCustomer(user).orElse(null);
    }
}
