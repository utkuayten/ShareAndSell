package org.cs320.ozyegin.controller;

import java.util.List;

import org.cs320.ozyegin.data_layer.AdvertRepository;
import org.cs320.ozyegin.model.*;
import org.cs320.ozyegin.service.*;
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
	private AdvertRepository advertRepository;
	@Autowired
	private WalletService walletService;


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
	@GetMapping("/marketplace")
	public String marketPlace(Model model){
		List<Advertisement> adverts = advertRepository.findAllAdverts();
		model.addAttribute("advertisements", adverts);
		return "marketplace";
	}
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		User new_user = userService.saveUser(user);
		if (new_user != null) {
			session.setAttribute("msg", "Register successfully");
			walletService.saveWallet(new Wallet(),new_user);
		} else {
			session.setAttribute("msg", "Error : Something went wrong !");
		}
		return "redirect:/";
	}



}