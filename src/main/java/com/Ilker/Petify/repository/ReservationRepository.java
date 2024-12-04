package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime localDateTime);

    Optional<Reservation> findReservationById(Long id);

    boolean existsByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    boolean existsByStartTimeBetweenAndPetBarberId(LocalDateTime startTime, LocalDateTime endTime, Long petBarberId);

    List<Reservation> findByStartTimeBetweenAndPetBarberId(LocalDateTime startTime, LocalDateTime endTime, Long barberId);
}