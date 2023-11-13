package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.services.UserService;

/**
 * Provides REST endpoints for typical website functions and interactions with the user database.
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
     * Redirects users to the home page.
     * @return the file containing the home page.
     */
    @GetMapping("/")
    public String homepage(){
        return "index";
    }

    /**
     * Redirects users to the login page.
     * @return the file containing the login page.
     */
    @GetMapping("/login")
    public String login() {
        // FIXME: do not let user return to login page if already signed in.
        return "login";
    }

    /**
     * Redirects users to the registration page.
     * @return the file containing the registration page.
     */
    @GetMapping("/register")
    public String register(){
        return "registration";
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
