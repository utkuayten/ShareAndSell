package org.cs320.ozyegin.controller.web;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.controller.web.dto.UserRegistrationDto;
import org.cs320.ozyegin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "login";
    }

    @PostMapping
    public String logIntoUser(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) throws Exception {
        User user = userService.find(registrationDto);

        if( user == null ){
            System.out.println("Mail is not registered");
            return "login";
        }

        if(user.getPassword().equals(registrationDto.getPassword()) )
            return "profile";
        else
            return "login";


    }
}