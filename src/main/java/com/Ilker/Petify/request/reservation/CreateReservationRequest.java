package com.Ilker.Petify.request.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReservationRequest {

    @NotBlank(message = "Please choose the pet.")
    private Long petId;

    @NotBlank(message = "Please choose the barber." )
    private Long barberId;

    @NotBlank(message = "Please choose the reservation start time." )
    private String startTime;

}
