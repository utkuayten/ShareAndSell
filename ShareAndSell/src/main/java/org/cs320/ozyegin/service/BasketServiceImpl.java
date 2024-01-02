package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.BasketRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private AdvertService advertService;

    @Override
    public void saveBasket(Basket basket, Advertisement advertisement, int quantity, User user) throws IOException {
        basket.setBuyer_id(user.getId());
        basket.setProduct_id(advertisement.getId());
        Basket alreadyExists = basketRepository.findBasketByProductId(advertisement.getId(), user.getId());
        if (alreadyExists == null) {
            basket.setQuantity(quantity);
            basketRepository.save(basket);
        } else {
            int qt = alreadyExists.getQuantity();
            basketRepository.updateBasketQuantityByProductId(advertisement.getId(), user.getId(), quantity + qt);
        }
    }

    @Override
    public List<Basket> findBasketByUser(User user) {
        return basketRepository.findBasketByBuyer(user.getId());
    }

    @Override
    public void deleteFromBasketByBasket(Basket basket) {
        basketRepository.deleteByBasketId(basket.getId());
    }

    @Override
    public Basket findBasketById(Long id) {
        return basketRepository.findBasketById(id);
    }

    @Override
    public int totalPriceCalculator(List<Basket> basketList) {
        int total = 0;
        Advertisement advertisement = new Advertisement();
        for (Basket item : basketList) {
            total += item.getQuantity() * advertService.findAdvertByID(item.getProduct_id()).getPrice();
        }
        return total;
    }


}
