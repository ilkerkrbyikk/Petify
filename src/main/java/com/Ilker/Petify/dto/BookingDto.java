package com.Ilker.Petify.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long hotelId;

    private Long customerId;

    private Long petId;

}
