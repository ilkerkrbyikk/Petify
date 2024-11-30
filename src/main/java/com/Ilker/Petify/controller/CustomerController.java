package com.Ilker.Petify.controller;

import com.Ilker.Petify.dto.BookingDto;
import com.Ilker.Petify.entity.Booking;
import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.BookingService;
import com.Ilker.Petify.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Tag(name = "Customer")
public class CustomerController {

    private final BookingService bookingService;
    private final CommentService commentService;


    @Operation(
            summary = "Books a hotel based on the provided booking details.",
            description = "This endpoint allows a customer to book a hotel by providing the necessary booking details in the request body. " +
                    "Upon successful booking, the booking information will be returned in the response."
    )
    @PostMapping("/booking")
    public ResponseEntity<ApiResponse> postBook(@RequestBody BookingDto bookingDto){
        Booking booking = bookingService.bookHotel(bookingDto);
        return ResponseEntity.ok(new ApiResponse("Success.", booking));
    }

    @Operation(
            summary = "Cancels a booking by its ID.",
            description = "This endpoint allows a customer to cancel an existing booking by providing the booking ID. " +
                    "A successful cancellation will return a success message."
    )
    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<ApiResponse> cancelBook(@PathVariable Long id){
        bookingService.cancel(id);
        return ResponseEntity.ok(new ApiResponse("Success.", null));
    }

    @Operation(
            summary = "Updates an existing booking.",
            description = "This endpoint allows a customer to update an existing booking by providing the booking ID and the new booking details. " +
                    "The updated booking information will be returned in the response."
    )
    @PutMapping("/booking/update/{id}")
    public ResponseEntity<ApiResponse> updateBook(@RequestBody BookingDto bookingDto, @PathVariable Long id){
        Booking booking = bookingService.updateBook(bookingDto,id);
        return ResponseEntity.ok(new ApiResponse("Success.",booking));
    }

 /*   @Operation(
            summary = "Adds a comment to a specified entity.",
            description = "This endpoint allows a customer to add a comment to a specific entity type, which can be a hotel, pet barber, or pet sitter. " +
                    "The request must include the entity type and the corresponding ID of the entity to which the comment is being added. " +
                    "If the entity type is invalid, a bad request response will be returned."
    )
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
    }*/


}