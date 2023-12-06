package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project11.amazinbookstore.model.*;
import project11.amazinbookstore.services.ItemRecommendationService;

import java.util.List;

@org.springframework.stereotype.Controller
@Slf4j
public class RecommendationController {
    ItemRecommendationService recommendationService;

    @Autowired
    public RecommendationController(ItemRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendations")
    public String recommendationsPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Book> recommendedBookList = recommendationService.getRecommendation(username);
        model.addAttribute("Recommendations", recommendedBookList);

        AuthoritiesDTO authoritiesDTO = new AuthoritiesDTO(auth);
        model.addAttribute("authorities", authoritiesDTO);
        model.addAttribute("bookCardDTO", new BookCardDTO(auth, BookCardDTO.Context.RECOMMENDATIONS));

        if (authoritiesDTO.isUser()) {
            return "recommendations";
        }
        return "redirect:/";
    }
}
