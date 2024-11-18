package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<ApiResponse> getAvailableHotels(){
        List<Hotel> hotels = hotelService.getAvailableHotels();
        return ResponseEntity.ok(new ApiResponse("Success.",hotels));
    }


}

