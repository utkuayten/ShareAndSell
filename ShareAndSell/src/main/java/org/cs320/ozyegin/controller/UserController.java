package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertService advertService;

    @GetMapping("/user/sell")
    public String advertPanel(Principal p, Model m, Advertisement advertisement){
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        m.addAttribute("advertisement", advertisement);
        return "advertPanel";
    }

    @PostMapping("/user/sellProduct")
    public String advertPanelSell(@ModelAttribute Advertisement advert, Principal p){
        User seller_user = userRepository.findByEmail(p.getName());
        advert.setSeller_id(seller_user.getId());
        Advertisement new_advert = advertService.saveAdvertisement(advert);
        return "redirect:/user/sell";
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/user/home")
    public String home(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        return "index";
    }
}
