package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.request.hotel.AddHotelRequest;
import com.Ilker.Petify.request.hotel.UpdateHotelRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/c-customer")
@RequiredArgsConstructor
public class CorporateCustomerController {

    private final HotelService hotelService;

    @GetMapping("/hotel/all")
    public ResponseEntity<ApiResponse> getAllHotels(){
        List<Hotel> hotels = hotelService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",hotels));
    }

    @PostMapping("/hotel/add")
    public ResponseEntity<ApiResponse> addHotel(@RequestBody AddHotelRequest request){
        Hotel hotel = hotelService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",hotel));
    }

    @PutMapping("/hotel/update/{id}")
    public ResponseEntity<ApiResponse> updateHotel(@RequestBody UpdateHotelRequest request,
                                                   @PathVariable Long id){
        Hotel hotel = hotelService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",hotel));
    }

    @DeleteMapping("/hotel/delete/{id}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable Long id){
        hotelService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.", id));
    }



}
