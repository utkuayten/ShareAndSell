package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/user/marketplace")
public class SearchAndFilterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertService advertService;

    @GetMapping("/user/marketplace")
    public String showMarketplace(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "hideMine", defaultValue = "false") boolean hideMine,
            Model model,
            Principal principal
    ) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        List<Advertisement> advertisements;

        if (hideMine) {
            // If the checkbox is checked, exclude advertisements by the current user
            advertisements = advertService.findAllAdvertisementsExcludingUser(user.getId());
        } else {
            if (query != null && !query.isEmpty()) {
                // If there is a search query, filter advertisements by title
                advertisements = advertService.findByPartialTitle(query);
            } else {
                // Retrieve all advertisements if no query is provided and checkbox is not checked
                advertisements = advertService.findAllAdvertisements();
            }
        }

        model.addAttribute("advertisements", advertisements);
        model.addAttribute("hideMine", hideMine);

        return "marketplace";
    }



    @GetMapping("/user/details/{id}")
    public String showProductDetails(@PathVariable Long id, Model model, Principal principal) {
        // Log the received ID
        System.out.println("Received ID: " + id);

        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        Advertisement advertisement = advertService.findAdvertByID(id);

        System.out.println("Retrieved Advertisement: " + advertisement.toString());

        model.addAttribute("advertisement", advertisement);

        return "productDetails";
    }






}
