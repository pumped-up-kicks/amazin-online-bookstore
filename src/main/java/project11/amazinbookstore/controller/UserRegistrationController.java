package project11.amazinbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.services.UserService;

@org.springframework.stereotype.Controller
@Slf4j
public class UserRegistrationController {

    private UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/")
    public String homepage(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        // FIXME: do not let user return to login page if already signed in.
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "registration";
    }

    @RequestMapping(value = "/processing-registration", method = RequestMethod.GET)
    public String registerNewUser(String username, String password){
        userService.addNewUser(username, password, Role.USER);

        // TODO: handle failed account creation
        return "redirect:/login";
    }
}
