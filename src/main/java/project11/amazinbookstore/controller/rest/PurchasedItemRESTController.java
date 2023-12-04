package project11.amazinbookstore.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project11.amazinbookstore.model.PurchasedItem;
import project11.amazinbookstore.services.PurchasedItemService;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class PurchasedItemRESTController {

    private PurchasedItemService service;

    @Autowired
    public PurchasedItemRESTController(PurchasedItemService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<PurchasedItem> findAllUserPurchasedItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.findAllPurchasedItems(auth.getName());
    }
}
