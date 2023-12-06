package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project11.amazinbookstore.model.*;
import project11.amazinbookstore.services.PurchasedItemService;

import java.util.List;

/**
 * Provides endpoints for purchase history URLs.
 */
@org.springframework.stereotype.Controller
@Slf4j
public class PurchaseHistoryController {
    PurchasedItemService purchasedItemService;

    /**
     * Creates a purchase history controller
     * @param purchasedItemService
     */
    @Autowired
    public PurchaseHistoryController(PurchasedItemService purchasedItemService) {
        this.purchasedItemService = purchasedItemService;
    }

    @GetMapping("/purchasehistory")
    public String purchaseHistoryPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<PurchasedItem> purchasedItemList = purchasedItemService.findAllPurchasedItems(username);
        model.addAttribute("PurchasedItems", purchasedItemList);

        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO(auth);
        model.addAttribute("authorities", authoritiesDTO);
        model.addAttribute("bookCardDTO", new BookCardDTO(auth, BookCardDTO.Context.PURCHASE_HISTORY));

        if (authoritiesDTO.isUser()) {
            return "purchaseHistory";
        }
        return "redirect:/";
    }
}
