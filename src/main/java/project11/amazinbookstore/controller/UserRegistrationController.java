package project11.amazinbookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.services.UserService;

@org.springframework.stereotype.Controller
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

    @GetMapping("/register")
    public String register(){
        return "registration";
    }

    @RequestMapping(value = "/processing-registration", method = RequestMethod.GET)
    public String registerNewUser(String username, String password){
        userService.addNewUser(username, password, Role.USER);

        return "redirect:/register";
    }
}
