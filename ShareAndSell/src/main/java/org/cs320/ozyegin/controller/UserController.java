package org.cs320.ozyegin.controller;

import jakarta.servlet.http.HttpSession;
import org.cs320.ozyegin.data_layer.ImageRepository;
import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.dtonutil.BasketDto;
import org.cs320.ozyegin.model.*;
import org.cs320.ozyegin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

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
    private BasketService basketService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/user/sell")
    public String advertPanel(Principal p, Model m, Advertisement advertisement){
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        m.addAttribute("advertisement", advertisement);
        return "advertPanel";
    }

    @PostMapping("/user/sellProduct")
    public String advertPanelSell(@RequestParam("file") MultipartFile file, @ModelAttribute Advertisement advert, Principal p,Model m) throws IOException, IOException {
        User seller_user = userRepository.findByEmail(p.getName());
        advert.setSeller_id(seller_user.getId());
        advert.setSeller_name(seller_user.getName());
        if (!(Objects.equals(file.getContentType(), "image/png") || Objects.equals(file.getContentType(), "image/jpeg"))) {
            return "redirect:/user/sell?error";
        }
        advertService.saveAdvertisement(advert, file);
        return "redirect:/user/sell";
    }

    @PostMapping("/user/checkout")
    public String userCheckout(
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("buildingName") String buildingName,
            @RequestParam("total_price") int totalPrice,
            @RequestParam("wallet_balance") int walletBalance,
            @RequestParam("state") String state,
            HttpSession session,
            Principal p
    ) {
        String address = country + ", " + city + ", " + street + ", " + state + ", " + buildingName;

        User user = userService.findByEmail(p.getName());

        if (totalPrice > walletBalance) {
            session.setAttribute("msg", "Insufficient balance.");
            return "redirect:/user/basket";
        } else {
            session.setAttribute("msg", "Order is successful.");
            List<Basket> basket = basketService.findBasketByUser(user);
            for (Basket item:basket) {
            int i = advertService.getQuantityById(item.getProduct_id()) - item.getQuantity();
            if(i==0){
                advertService.updateAdvertStat(item.getProduct_id());
                advertService.updateAdvertQuantityByProductId(item.getProduct_id(),i);
            }
            else{
                advertService.updateAdvertQuantityByProductId(item.getProduct_id(), i);
            }


            }


            transactionService.createMultipleTransactionsByBasket(basket, address);
            basketService.deleteFromBasketByUser(user);





        }
        return "redirect:/user/basket";
    }

    @GetMapping("/user/removeItem")
    public String removeItem(@RequestParam("basketId") Long basketId, Principal p) {
        Basket basket = basketService.findBasketById(basketId);
        basketService.deleteFromBasketByBasket(basket);
        return "redirect:/user/basket";
    }

    @PostMapping("/user/addBasket/{advertisementId}")
    public String placeOrder(@PathVariable("advertisementId") Long advertID, @RequestParam("quantity") int quantity, Principal p) throws IOException {
        User buyer = userRepository.findByEmail(p.getName());
        Advertisement advert = advertService.findAdvertByID(advertID);
        basketService.saveBasket(new Basket(), advert, quantity, buyer);
        return "redirect:/user/marketplace";
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        Wallet wallet = walletService.findWalletByOwner(user);
        m.addAttribute("wallet",wallet);
        if(imageRepository.imageCheck(user.getId()) != 0) {
            Image image = imageService.findImageByOwner_id(user);
            String base64Image = java.util.Base64.getEncoder().encodeToString(image.getImageData());
            String dataUri = "data:image/jpeg;base64," + base64Image;

            m.addAttribute("image", dataUri);
        }
        return "profile";
    }


    @PostMapping("/user/profile/confirmBalance")
    public String confirmBalance(@RequestParam("addBalance") int addBalance,Principal p) {
        User user = userRepository.findByEmail(p.getName());
        Wallet wallet = walletService.findWalletByOwner(user);
        System.out.println("New Balance: " + addBalance);
        if (wallet != null) {
            walletService.updateBalance(wallet,addBalance);
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/user/home")
    public String home(Principal p, Model m) {
        User user = userRepository.findByEmail(p.getName());
        m.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/user/basket")
    public String basketPage(Principal p, Model model) {
        User user = userRepository.findByEmail(p.getName());
        Wallet wallet = walletService.findWalletByOwner(user);
        List<Basket> basketList = basketService.findBasketByUser(user);
        List<BasketDto> basketAdverts = basketService.basketAdverts(basketList);
        int totalPrice = basketService.totalPriceCalculator(basketList);
        model.addAttribute("wallet", wallet);
        model.addAttribute("user", user);
        model.addAttribute("basket", basketAdverts);
        model.addAttribute("total_price", totalPrice);
        return "basketpage";
    }

    @PostMapping("/user/saveImage")
    public String saveImage(@RequestParam("file") MultipartFile file, Principal p) throws IOException {
        User user = userRepository.findByEmail(p.getName());
        if(imageRepository.imageCheck(user.getId()) != 0) {
            imageRepository.deleteByOwner_id(user.getId());
        }
        imageService.uploadImageForProfile(file, user.getId());
        return "redirect:/user/profile";
    }


    @PostMapping("/user/buy/{id}")
    public String buyProduct(
            @PathVariable Long id,
            @RequestParam(name = "quantity", defaultValue = "1") int quantity,
            Model model,
            Principal principal
    ) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        // Call the service method to handle the buying logic
        boolean success = advertService.buyProduct(id, quantity);

        if (success) {
            // Update the model with a success message or any other necessary information
            model.addAttribute("buySuccess", true);
        } else {
            // Update the model with a failure message or any other necessary information
            model.addAttribute("buyFailure", true);
        }

        // Redirect back to the product details page or any other appropriate page
        return "redirect:/user/details/" + id;
    }







    //    @GetMapping ("/user/basket")
//    public String showBasket(Principal p, Model m) {
//        User user = userRepository.findByEmail(p.getName());
//        m.addAttribute("user", user);
//        List<Transaction> userBasket = transactionService.findBasket(user);
//        m.addAttribute("basketAds", userBasket);
//        return "basketpage";
//   }


}

//TODO: Redundancy problem can be solve by doing this ?
//private void addUserDetails(Model model, Principal p) {
//    User user = getUserByEmail(p.getName());
//    model.addAttribute("user", user);
//}
