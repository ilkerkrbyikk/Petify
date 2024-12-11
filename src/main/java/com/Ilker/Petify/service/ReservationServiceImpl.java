package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.entity.Reservation;
import com.Ilker.Petify.exception.PetBarberNotFoundException;
import com.Ilker.Petify.exception.PetNotFoundException;
import com.Ilker.Petify.exception.ReservationNotFoundException;
import com.Ilker.Petify.exception.TimeSlotTakenException;
import com.Ilker.Petify.repository.PetBarberRepository;
import com.Ilker.Petify.repository.PetRepository;
import com.Ilker.Petify.repository.ReservationRepository;
import com.Ilker.Petify.request.reservation.CreateReservationRequest;
import com.Ilker.Petify.request.reservation.UpdateReservationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PetRepository petRepository;
    private final PetBarberRepository petBarberRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public List<Reservation> getAll() {
        logger.info("Fetching all reservations");
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        logger.info("Fetching reservation with ID: {}", id);
        return reservationRepository.findById(id);
    }

    @Override
    @Transactional
    public Reservation create(CreateReservationRequest request) {
        logger.info("Creating reservation for pet ID: {}", request.getPetId());

        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime(), FORMATTER);

        LocalDateTime endTime = startTime.plusMinutes(30);

        PetBarber petBarber = petBarberRepository.findById(request.getBarberId())
                .orElseThrow(() -> {
                    logger.error("Barber not found with ID: {}", request.getBarberId());
                    return new PetBarberNotFoundException("Barber not found with given ID: " + request.getBarberId());
                });

        Pet pet = petRepository.findPetById(request.getPetId())
                .orElseThrow(() -> {
                    logger.error("Pet not found with ID: {}", request.getPetId());
                    return new PetNotFoundException("Pet not found with given ID: " + request.getPetId());
                });

        if (!isAvailable(startTime, endTime, request.getBarberId())) {
            logger.error("Time slot from {} to {} is already taken.", startTime, endTime);
            throw new TimeSlotTakenException("The time slot from " + startTime + " to " + endTime + " is already taken.");
        }

        Reservation reservation = new Reservation();
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setPetBarber(petBarber);
        reservation.setPet(pet);
        reservation.setTotalPrice(petBarber.getPrice());

        logger.info("Reservation created successfully with ID: {}", reservation.getId());
        return reservationRepository.save(reservation);

    }

    @Override
    @Transactional
    public Reservation update(UpdateReservationRequest request, Long id) {
        logger.info("Updating reservation with ID: {}", id);
        checkReservationExistsById(id);

        Reservation reservation = reservationRepository.findReservationById(id)
                .orElseThrow(() -> {
                    logger.error("Reservation not found with ID: {}", id);
                    return new ReservationNotFoundException("Reservation not found with given ID: " + id);
                });

        Pet pet = petRepository.findPetById(request.getPetId())
                .orElseThrow(() -> {
                    logger.error("(UpdateReservation) Pet not found with ID: {}", request.getPetId());
                    return new PetNotFoundException("Pet not found with given ID: " + request.getPetId());});

        PetBarber petBarber = petBarberRepository.findById(request.getBarberId())
                .orElseThrow(() -> {
                    logger.error("(UpdateReservation) Barber not found with ID: {}", request.getBarberId());
                    return new PetBarberNotFoundException("Barber not found with given ID: " + request.getBarberId());
                });
        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime(), FORMATTER);
        LocalDateTime endTime = startTime.plusMinutes(30);

        if (!isAvailable(startTime, endTime, request.getBarberId())) {
            logger.error("(UpdateReservation) Time slot from {} to {} is already taken.", startTime, endTime);
            throw new TimeSlotTakenException("The time slot from " + startTime + " to " + endTime + " is already taken.");
        }

        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setPetBarber(petBarber);
        reservation.setPet(pet);
        reservation.setTotalPrice(petBarber.getPrice());

        logger.info("Reservation updated successfully with ID: {}", reservation.getId());
        return reservationRepository.save(reservation);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("Attempting to delete reservation with ID: {}", id);
        checkReservationExistsById(id);
        reservationRepository.deleteById(id);
        logger.info("Reservation with ID: {} deleted successfully", id);
    }

    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime, Long barberId) {

        List<Reservation> existingReservations = reservationRepository.findByStartTimeBetweenAndPetBarberId(
                startTime, endTime, barberId);
        return existingReservations.isEmpty();
    }

    //* private method unutma.
    private void checkReservationExistsById(Long id) {
        if (!reservationRepository.existsById(id)) {
            logger.error("(Check Reservation Function) Reservation not found with ID: {}", id);
            throw new ReservationNotFoundException("Reservation not found with ID: " + id);
        }
    }
}
