package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Tag(name = "Hotel")
public class HotelController {

    private final HotelService hotelService;


    @Operation(
            summary = "Retrieves a list of available hotels.",
            description = "This endpoint returns a list of hotels that are currently available for booking. " +
                    "A successful response will include the details of the available hotels."
    )
    @GetMapping("/hotels")
    public ResponseEntity<ApiResponse> getAvailableHotels(){
        List<Hotel> hotels = hotelService.getAvailableHotels();
        return ResponseEntity.ok(new ApiResponse("Success.",hotels));
    }


}

