package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;
    Image getImage(Long id);

}
