package com.Ilker.Petify.service;


import com.Ilker.Petify.entity.Image;
import com.Ilker.Petify.exception.ImageNotFoundException;
import com.Ilker.Petify.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;


    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImageData(file.getBytes());
        image.setImageName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        return imageRepository.save(image);
    }

    @Override
    public Image getImage(Long id){
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new ImageNotFoundException("Image not found with given ID." +id));
    }
}
