package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.request.hotel.AddHotelRequest;
import com.Ilker.Petify.request.hotel.UpdateHotelRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/c-customer")
@RequiredArgsConstructor
@Tag(name = "Corporate Customer")
public class CorporateCustomerController {

    private final HotelService hotelService;


    @Operation(
            summary = "Fetches a list of all available hotels."
    )
    @GetMapping("/hotel/all")
    public ResponseEntity<ApiResponse> getAllHotels(){
        List<Hotel> hotels = hotelService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",hotels));
    }

    @Operation(
            summary = "Adds a new hotel with the provided details."
    )
    @PostMapping("/hotel/add")
    public ResponseEntity<ApiResponse> addHotel(@Valid @RequestBody AddHotelRequest request){
        Hotel hotel = hotelService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",hotel));
    }

    @Operation(
            summary = "Updates the details of an existing hotel identified by its ID."
    )
    @PutMapping("/hotel/update/{id}")
    public ResponseEntity<ApiResponse> updateHotel(@Valid @RequestBody UpdateHotelRequest request,
                                                   @PathVariable Long id){
        Hotel hotel = hotelService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",hotel));
    }

    @Operation(
            summary = "Deletes a hotel identified by its ID."
    )
    @DeleteMapping("/hotel/delete/{id}")
    public ResponseEntity<ApiResponse> deleteHotel(@Valid @PathVariable Long id){
        hotelService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.", id));
    }

    @Operation(
            summary = "Uploads images for a hotel identified by its ID."
    )
    @PostMapping("/hotel/{hotelId}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long hotelId, @RequestParam("file")MultipartFile file){
        try {
            hotelService.uploadHotelImage(hotelId,file);
            return ResponseEntity.ok("Success.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An error occurred while uploading the image: " + e.getMessage());
        }
    }


    @GetMapping("/hotel/{hotelId}/image")
    public ResponseEntity<String> getHotelImage(@PathVariable Long hotelId) {
        String base64Image = hotelService.getHotelImageBase64(hotelId);

        if (base64Image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(base64Image);
    }



}
