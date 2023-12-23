package org.cs320.ozyegin.controller.web;

import org.cs320.ozyegin.controller.web.dto.UserRegistrationDto;
import org.cs320.ozyegin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	private UserService userService;

	public MainController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "loginPage";
	}
	
	@GetMapping("/")
	public String home() {
		return "loginPage";
	}


}
