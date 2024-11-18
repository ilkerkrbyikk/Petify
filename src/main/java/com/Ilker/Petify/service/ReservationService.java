package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Reservation;
import com.Ilker.Petify.request.reservation.CreateReservationRequest;
import com.Ilker.Petify.request.reservation.UpdateReservationRequest;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> getAll();
    Optional<Reservation> getReservationById(Long id);
    Reservation create(CreateReservationRequest request);
    Reservation update(UpdateReservationRequest request, Long id);
    void delete(Long id);
}
