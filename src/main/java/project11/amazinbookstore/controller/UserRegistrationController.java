package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.services.UserService;

/**
 * Provides endpoints for typical website functions and interactions with the user database.
 * @author Zakaria Ismail, Patrick Liu
 * @version 1.0
 */
@org.springframework.stereotype.Controller
@Slf4j
public class UserRegistrationController {

    private UserService userService;

    /**
     * Creates a UserRegistrationController object corresponding to a given service for interactions
     * with the user database.
     * @param userService the service for the user database.
     */
    @Autowired
    public UserRegistrationController(UserService userService){
        this.userService = userService;
    }

    /**
     * Redirects users to the login page.
     * @return the file containing the login page.
     */
    @GetMapping("/login")
    public String login() {
        // FIXME: do not let user return to login page if already signed in.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"))) {
            // Anonymous user
            log.info("Anonymous user detected");
            return "login";
        }
        // Logged in user
        return "redirect:/";
    }

    /**
     * Redirects users to the registration page.
     * @return the file containing the registration page.
     */
    @GetMapping("/register")
    public String register(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"))) {
            // Anonymous user
            log.info("Anonymous user detected");
            return "registration";
        }
        // Logged-in user
        return "redirect:/";
    }

    /**
     * Registers a new user with the user service.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return the response, redirect to  the login page on success, error notification on failure.
     */
    @RequestMapping(value = "/processing-registration", method = RequestMethod.GET)
    public String registerNewUser(String username, String password){
        boolean accountCreated = userService.addNewUser(username, password, Role.USER);

        if (accountCreated)
            return "redirect:/login";
        return "redirect:/register?usernameExists";
    }
}
