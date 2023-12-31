package org.cs320.ozyegin.service;

import org.cs320.ozyegin.model.Image;
import org.cs320.ozyegin.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {


    public Image uploadImage(MultipartFile file, Long id)throws IOException;
    public Image findImageByOwner_id(User user);

    public Optional<byte[]> getImage(User user);

    public Optional<byte[]> getImageDataByUserId(Long userId);
}
