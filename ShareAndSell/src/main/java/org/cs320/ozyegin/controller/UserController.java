package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;
import org.cs320.ozyegin.service.AdvertService;
import org.cs320.ozyegin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private WalletService walletService;


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
    public String profile(@RequestParam(value ="showButton", required = false) Boolean showButton,Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        Wallet wallet = walletService.findWalletByOwner_id(user);
        m.addAttribute("wallet",wallet);
        m.addAttribute("showButton", showButton != null && showButton);
        return "profile";
    }

    @GetMapping("/user/home")
    public String home(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/user/marketplace")
    public String marketPlace(Principal p,Model model){
        User user = userRepository.findByEmail(p.getName());
        model.addAttribute("user", user);
        List<Advertisement> adverts = advertService.findAllAdvertisements();
        model.addAttribute("advertisements", adverts);
        return "marketplace";
    }


    @PostMapping ("/user/placeOrder/{advertisementId}")
    public String placeOrder(@PathVariable("advertisementId") Long advertisementId, Model model) {
        // Logic to place the order for the given advertisement ID
        // Assuming you have logic here to perform the order placement

        // Set the 'showButton' variable to true to display the confirmation button
        model.addAttribute("showButton", true); // This sets showButton to true for displaying the button
        System.out.println(advertisementId);
        // You might also need to retrieve and set other necessary model attributes for the profile page

        // Redirect to the profile page after placing the order
        return "redirect:/user/profile?showButton=true"; // Replace with your actual profile URL
    }


}

//TODO: Redundancy problem can be solve by doing this ?
//private void addUserDetails(Model model, Principal p) {
//    User user = getUserByEmail(p.getName());
//    model.addAttribute("user", user);
//}
