package com.Ilker.Petify.controller;

import com.Ilker.Petify.repository.HotelRepository;
import com.Ilker.Petify.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class FileUploadController {

    private HotelRepository hotelRepository;
    private PetSitterRepository petSitterRepository;


}
