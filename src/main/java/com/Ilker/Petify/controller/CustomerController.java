package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.BookingDto;
import com.Ilker.Petify.entity.Booking;
import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.BookingService;
import com.Ilker.Petify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final BookingService bookingService;
    private final CommentService commentService;


    @PostMapping("/booking")
    public ResponseEntity<ApiResponse> postBook(@RequestBody BookingDto bookingDto){
        Booking booking = bookingService.bookHotel(bookingDto);
        return ResponseEntity.ok(new ApiResponse("Success.", booking));
    }

    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<ApiResponse> cancelBook(@PathVariable Long id){
        bookingService.cancel(id);
        return ResponseEntity.ok(new ApiResponse("Success.", null));
    }

    @PutMapping("/booking/update/{id}")
    public ResponseEntity<ApiResponse> updateBook(@RequestBody BookingDto bookingDto, @PathVariable Long id){
        Booking booking = bookingService.updateBook(bookingDto,id);
        return ResponseEntity.ok(new ApiResponse("Success.",booking));
    }


    @PostMapping("/comment/add")
    public ResponseEntity<?> addHotelComment(@RequestBody AddCommentRequest request, @PathVariable Long id){
        switch (request.getEntityType()) {
            case "hotel":
                commentService.addHotelComment(request,id);
                break;
            case "petBarber":
                commentService.addPetBarberComment(request,id);
                break;
            case "petSitter":
                commentService.addPetSitterComment(request, id);
                break;

            default:
                return ResponseEntity.badRequest().body(new ApiResponse("Bad Request! Invalid entity type.",null));
        }
        return ResponseEntity.status(CREATED).body("Success.");
    }


}