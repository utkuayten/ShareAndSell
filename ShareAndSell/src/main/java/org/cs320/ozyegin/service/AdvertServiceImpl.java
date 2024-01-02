package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.AdvertRepository;
import org.cs320.ozyegin.dtonutil.ImageUtil;
import org.cs320.ozyegin.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Advertisement> findAllAdvertisementsExcludingUser(Long userId) {return advertRepository.findAllAdvertsExcludingUser(userId);}

    @Override
    public List<Advertisement> findByPartialTitle(String title) {
        return advertRepository.findByPartialTitle(title);
    }

    @Override
    public Advertisement findAdvertByID(Long id) {return advertRepository.findByID(id);}

    @Override
    public int getQuantityById(Long id){return advertRepository.getQuantById(id); }

    public void updateAdvertQuantityByProductId(Long product_id, int quantity){ advertRepository.updateAdvertQuantityByProductId(product_id, quantity);}

    public void updateAdvertStat(Long id){advertRepository.updateAdvertStat(id);}
    @Transactional
    public boolean buyProduct(Long productId, int quantity) {
        Advertisement advertisement = advertRepository.findById(productId).orElse(null);

        if (advertisement != null && advertisement.getQuantity() >= quantity) {
            // Sufficient quantity available, update the quantity
            int newQuantity = advertisement.getQuantity() - quantity;
            advertisement.setQuantity(newQuantity);
            advertRepository.save(advertisement);
            return true;
        }

        return false;
    }
}

