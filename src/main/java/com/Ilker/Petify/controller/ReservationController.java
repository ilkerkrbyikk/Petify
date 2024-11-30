package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Reservation;
import com.Ilker.Petify.request.reservation.CreateReservationRequest;
import com.Ilker.Petify.request.reservation.UpdateReservationRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/reservation")
@RequiredArgsConstructor
@Tag(name = "Pet Barber Reservations ")
public class ReservationController {

    private final ReservationService reservationService;


    @Operation(
            summary = "Retrieves all reservations.",
            description = "This endpoint fetches a list of all reservations in the system. " +
                    "The response will include details of each reservation, such as the reservation date, " +
                    "associated pet sitter, and customer information."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Reservation> reservations = reservationService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", reservations));
    }

    @Operation(
            summary = "Retrieves a reservation by its ID.",
            description = "This endpoint fetches the details of a specific reservation identified by its ID. " +
                    "The response will include all relevant information about the reservation."
    )
    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApiResponse> getAvailable(@PathVariable Long id){
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(new ApiResponse("Success.", reservation));
    }

    @Operation(
            summary = "Creates a new reservation.",
            description = "This endpoint allows the creation of a new reservation. " +
                    "The request must include the necessary details in the request body. " +
                    "Upon successful creation, the newly created reservation will be returned."
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateReservationRequest request){
        Reservation reservation = reservationService.create(request);
        return ResponseEntity.ok(new ApiResponse("Success.",reservation));
    }

    @Operation(
            summary = "Updates an existing reservation.",
            description = "This endpoint allows updating an existing reservation identified by its ID. " +
                    "The request must include the updated details in the request body. " +
                    "Upon successful update, the updated reservation will be returned."
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateReservationRequest request, @PathVariable Long id){
        Reservation reservation = reservationService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.", reservation));
    }

    @Operation(
            summary = "Cancels a reservation.",
            description = "This endpoint allows the cancel of a reservation identified by its ID. " +
                    "Upon successful cancel, a success message will be returned."
    )    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        reservationService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}