package org.cs320.ozyegin.controller;

import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.model.*;
import org.cs320.ozyegin.service.AdvertService;
import org.cs320.ozyegin.service.ImageService;
import org.cs320.ozyegin.service.TransactionService;
import org.cs320.ozyegin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository userService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/user/sell")
    public String advertPanel(Principal p, Model m, Advertisement advertisement){
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        m.addAttribute("advertisement", advertisement);
        return "advertPanel";
    }

    @PostMapping("/user/sellProduct")
    public String advertPanelSell(@RequestParam("file") MultipartFile file, @ModelAttribute Advertisement advert, Principal p) throws IOException {
        User seller_user = userRepository.findByEmail(p.getName());
        advert.setSeller_id(seller_user.getId());
        if (!file.getContentType().equals("image/png")) {
            return "redirect:/user/sell?error";
        }
        advertService.saveAdvertisement(advert, file);
        return "redirect:/user/sell";
    }

    @PostMapping("/user/saveImage")
    public String profileImage(@RequestParam("file") MultipartFile file, Principal p) throws IOException {
        User user = userRepository.findByEmail(p.getName());
        imageService.uploadImageForProfile(file, user.getId());
        return "redirect:/user/profile";
    }

    @PostMapping("/user/placeOrder/{advertisementId}")
    public String placeOrder(Transaction t, @PathVariable("advertisementId") Long advertID, @RequestParam("quantity") int quantity, Principal p) {
        Advertisement advert = advertService.findAdvertByID(advertID);
        User seller = userService.findByID(advert.getSeller_id());
        User buyer = userRepository.findByEmail(p.getName());
        t.setQuantity(quantity);
        transactionService.saveTransaction(t,seller,buyer,advert);
        return "redirect:/user/marketplace";
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        Wallet wallet = walletService.findWalletByOwner_id(user);
        m.addAttribute("wallet",wallet);
        Optional<byte[]> imageData = imageService.getImageDataByUserId(user.getId());
        if (imageData.isPresent()) {
            String base64Image = java.util.Base64.getEncoder().encodeToString(imageData.get());
        String image = "data:image/*;base64," + base64Image;
            m.addAttribute("image", image);
        } else {
            m.addAttribute("image", "../check.png");
        }
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



//    @GetMapping ("/user/basket")
//    public String showBasket(Principal p, Model m) {
//        User user = userRepository.findByEmail(p.getName());
//        m.addAttribute("user", user);
//        List<Transaction> userBasket = transactionService.findBasket(user);
//        m.addAttribute("basketAds", userBasket);
//        return "basketpage";
//    }


}

//TODO: Redundancy problem can be solve by doing this ?
//private void addUserDetails(Model model, Principal p) {
//    User user = getUserByEmail(p.getName());
//    model.addAttribute("user", user);
//}
