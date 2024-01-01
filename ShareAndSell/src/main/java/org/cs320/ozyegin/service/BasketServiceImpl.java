package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.BasketRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private AdvertService advertService;

    @Override
    public Basket saveBasket(Basket basket, Advertisement advertisement, int quantity, User user) throws IOException {
        basket.setBuyer_id(user.getId());
        basket.setProduct_id(advertisement.getId());
        basket.setQuantity(quantity);
        return basketRepository.save(basket);
    }

    @Override
    public List<Basket> findBasketByUser(User user) {
        return basketRepository.findBasketByBuyer(user.getId());
    }

    @Override
    public double totalPriceByBasket(List<Basket> basketList) {
        double total_price = 0;
        for (Basket item : basketList) {
            Advertisement advert = advertService.findAdvertByID(item.getProduct_id());
            total_price += advert.getPrice() * item.getQuantity();
        }
        return total_price;
    }
}
