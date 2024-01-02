package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.Advertisement;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdvertService {

    Advertisement saveAdvertisement(Advertisement advertisement, MultipartFile file) throws IOException;
    List<Advertisement> findAllAdvertisements();

    List<Advertisement> findByPartialTitle(String title);

    Advertisement findAdvertByID(Long id);


}
