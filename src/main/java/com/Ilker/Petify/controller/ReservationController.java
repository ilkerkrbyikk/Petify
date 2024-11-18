package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Reservation;
import com.Ilker.Petify.request.reservation.CreateReservationRequest;
import com.Ilker.Petify.request.reservation.UpdateReservationRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Reservation> reservations = reservationService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", reservations));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApiResponse> getAvailable(@PathVariable Long id){
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(new ApiResponse("Success.", reservation));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateReservationRequest request){
        Reservation reservation = reservationService.create(request);
        return ResponseEntity.ok(new ApiResponse("Success.",reservation));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateReservationRequest request, @PathVariable Long id){
        Reservation reservation = reservationService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.", reservation));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        reservationService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}