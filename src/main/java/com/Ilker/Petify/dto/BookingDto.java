package com.Ilker.Petify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    //TODO: STRING OLMAYAN NOTBLANKLERIN HEPSİNİ NOTNULL VE NOTEMPTY YAP.

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotBlank(message = "Please enter check in date.")
    private LocalDate checkInDate;

    @NotBlank(message = "Please enter check out date.")
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
