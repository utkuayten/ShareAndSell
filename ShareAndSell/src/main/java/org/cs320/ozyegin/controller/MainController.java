package org.cs320.ozyegin.controller;

import java.security.Principal;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.repositories.UserRepository;
import org.cs320.ozyegin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

//	@ModelAttribute
//	public void commonUser(Principal p, Model m) {
//		if (p != null) {
//			String email = p.getName();
//			User user = userRepo.findByEmail(email);
//			m.addAttribute("user", user);
//		}
//
//	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signIn")
	public String login() {
		return "login";
	}

	@GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		System.out.println(user);
		m.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/user/home")
	public String home() {
		return "home";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

		// System.out.println(user);

		User u = userService.saveUser(user);

		if (u != null) {
			session.setAttribute("msg", "Register successfully");

		} else {
			session.setAttribute("msg", "Error : Something went wrong !");
		}
		return "register";
	}

}