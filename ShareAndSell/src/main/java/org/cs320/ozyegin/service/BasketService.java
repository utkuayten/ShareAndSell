package org.cs320.ozyegin.service;

import org.cs320.ozyegin.dtonutil.BasketDto;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BasketService {
    void saveBasket(Basket basket, Advertisement advertisement, int quantity, User user) throws IOException;

    List<Basket> findBasketByUser(User user);

    void deleteFromBasketByBasket(Basket basket);

    void deleteFromBasketByUser(User user);
    Basket findBasketById(Long id);

    int totalPriceCalculator(List<Basket> basketList);

    void deleteBasket(Basket basket);
    int findProductQuantInBasket(Long product_id, Long buyer_id);
    List<BasketDto> basketAdverts(List<Basket> basketList);
}
