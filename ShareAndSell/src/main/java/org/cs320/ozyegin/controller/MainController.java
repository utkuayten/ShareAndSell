package org.cs320.ozyegin.controller;

import java.security.Principal;

import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.service.AdvertService;
import org.cs320.ozyegin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdvertService advertService;


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
		User user = userRepository.findByEmail(p.getName());
		m.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/user/sell")
	public String advertPanel(Principal p, Model m,Advertisement advertisement){
		User user = userRepository.findByEmail(p.getName());
		//Setting the user. For profile info..
		m.addAttribute("user", user);
		//Setting the advertisement for loading init.
		m.addAttribute("advertisement", advertisement);

		return "advertPanel";
	}

	@GetMapping("/user/home")
	public String home(Principal p, Model m) {
		User user = userRepository.findByEmail(p.getName());
		m.addAttribute("user", user);
		return "index";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

		// System.out.println(user);

		User new_user = userService.saveUser(user);

		if (new_user != null) {
			session.setAttribute("msg", "Register successfully");

		} else {
			session.setAttribute("msg", "Error : Something went wrong !");
		}
		return "register";
	}



	@PostMapping("/user/sellProduct")
	public String advertPanelSell(@ModelAttribute Advertisement advert, @ModelAttribute User user, Principal p){
		System.out.println(advert);
		User seller_user = userRepository.findByEmail(p.getName());
		System.out.println(seller_user.getId());
		advert.setSeller_id(seller_user.getId());
		Advertisement new_advert = advertService.saveAdvertisement(advert);
		return "redirect:/user/sell";
	}
}