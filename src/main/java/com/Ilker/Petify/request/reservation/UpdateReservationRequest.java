package com.Ilker.Petify.request.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateReservationRequest {

    private Long petId;

    private Long barberId;

    private String startTime;

    private LocalDateTime endTime;
}
