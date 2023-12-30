package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.Advertisement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdvertService {

    Advertisement saveAdvertisement(Advertisement advertisement);
    List<Advertisement> findAllAdvertisements();

    Advertisement findAdvertByID(Long id);
}
