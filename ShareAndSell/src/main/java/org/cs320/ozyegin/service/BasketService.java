package org.cs320.ozyegin.service;

import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BasketService {
    Basket saveBasket(Basket basket, Advertisement advertisement, int quantity, User user) throws IOException;

    List<Basket> findBasketByUser(User user);

}
