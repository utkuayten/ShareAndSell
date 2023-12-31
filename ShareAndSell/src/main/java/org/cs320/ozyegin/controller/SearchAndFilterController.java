package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/marketplace")
public class SearchAndFilterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertService advertService;

    @GetMapping
    public String showMarketplace(
            @RequestParam(name = "query", required = false) String query,
            Model model,
            Principal principal
    ) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        List<Advertisement> Advertisements;
        if (query != null && !query.isEmpty()) {
            Advertisements = advertService.findByPartialTitle(query);
        } else {
            // Retrieve all advertisements if no query is provided
            Advertisements = advertService.findAllAdvertisements();
        }

        model.addAttribute("advertisements", Advertisements);
        return "marketplace";
    }
}
