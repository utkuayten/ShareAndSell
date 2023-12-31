package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.ImageRepository;
import org.cs320.ozyegin.dtonutil.ImageUtil;
import org.cs320.ozyegin.model.Image;
import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image uploadImageForProfile(MultipartFile file, Long id) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .owner_id(id)
                .imageData(ImageUtil.compressImage(file.getBytes())).build();
        image.setPurpose("FOR_PROFILE");
        imageRepository.save(image);
        return image;

    }

    @Override
    public Image findImageByOwner_id(User user) {
        Image dbImage = imageRepository.findByOwner_id(user.getId());

        return Image.builder()
                .name(dbImage.getName())
                .type(dbImage.getType())
                .owner_id(dbImage.getOwner_id())
                .imageData(ImageUtil.decompressImage(dbImage.getImageData())).build();

    }

    @Override
    public Optional<byte[]> getImage(User user) {
        return getImageDataByUserId(user.getId());
    }

    @Override
    public Optional<byte[]> getImageDataByUserId(Long userId) {
        Image dbImage = imageRepository.findByOwner_id(userId);
        return Optional.of(ImageUtil.decompressImage(dbImage.getImageData()));
    }

    @Override
    public Image uploadImageForProduct(MultipartFile file, Long id) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .owner_id(id)
                .imageData(ImageUtil.compressImage(file.getBytes())).build();
        image.setPurpose("FOR_PRODUCT");
        imageRepository.save(image);
        return image;
    }

}
