package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String directToLogin() {
		return "loginPage";
	}


	@PostMapping("/login")
	@ResponseBody
	public String loginUser(@RequestBody User user) {
		String name = user.getName();
		String password = user.getPassword();
		System.out.println(name);
		System.out.println(password);
		// Authenticate the user here
		if ("a".equals(name) && "a".equals(password)) {
			return "success";
		} else {
			return "failure";
		}
	}




	@GetMapping("/")
	public String directToMain() {
		return "mainPage";
	}

	@GetMapping("/register")
	public String directToSignUp() {
		return "signUpPage";
	}

	@GetMapping("/profile")
	public String directToProfile(){
		return "profilePage";
	}

}
