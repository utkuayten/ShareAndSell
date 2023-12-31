package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.AdvertRepository;
import org.cs320.ozyegin.dtonutil.ImageUtil;
import org.cs320.ozyegin.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService{

    @Autowired
    public AdvertRepository advertRepository;
    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement, MultipartFile file) throws IOException {
        System.out.println(advertisement);
        advertisement.setDate(new Date());
        advertisement.setImageData((ImageUtil.compressImage(file.getBytes())));
        return advertRepository.save(advertisement);
    }

    @Override
    public List<Advertisement> findAllAdvertisements() {
        return advertRepository.findAllAdverts();
    }

    @Override
    public Advertisement findAdvertByID(Long id) {
        return advertRepository.findByID(id);
    }
}
