package com.Ilker.Petify.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {



    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @FutureOrPresent(message = "Check-in date must be today or in the future.")
    @NotBlank(message = "Please enter check in date.")
    private LocalDate checkInDate;

    @NotBlank(message = "Please enter check out date.")
    @Future(message = "Check-out date must be in the future.")
    private LocalDate checkOutDate;

    @NotBlank(message = "Please enter hotel.")
    private Long hotelId;

/*
    @NotBlank(message = "Please enter hotel.")
*/
    private Long customerId;

    @NotBlank(message = "Please choose pet.")
    private Long petId;

}
