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
import org.springframework.stereotype.Service;

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

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation create(CreateReservationRequest request) {
        //TODO: DAHA ONCE AYNI SAATTE RESERVASYON ETMİŞ Mİ CHECKLE
        Reservation reservation = new Reservation();

        Pet pet = petRepository.findPetById(request.getPetId())
                .orElseThrow(()-> new PetNotFoundException("Pet not found with given ID: " + request.getPetId()));
        PetBarber petBarber = petBarberRepository.findById(request.getBarberId())
                .orElseThrow(()-> new PetBarberNotFoundException("Barber not found with given ID: " + request.getBarberId()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime(), formatter);

        if(isAvailable(LocalDateTime.parse(request.getStartTime()))){
            reservation.setPetBarber(petBarber);
            reservation.setPet(pet);
            reservation.setStartTime(LocalDateTime.parse(request.getStartTime()));
            return reservationRepository.save(reservation);

        }else {
            throw new TimeSlotTakenException("The time slot is already taken.");
        }

    }

    @Override
    public Reservation update(UpdateReservationRequest request, Long id) {
        isReservationExistsById(id);
        Reservation reservation = reservationRepository.findReservationById(id).get();

        Pet pet = petRepository.findPetById(request.getPetId())
                .orElseThrow(()-> new PetNotFoundException("Pet not found with given ID: " + request.getPetId()));
        PetBarber petBarber = petBarberRepository.findById(request.getBarberId())
                .orElseThrow(()-> new PetBarberNotFoundException("Barber not found with given ID: " + request.getBarberId()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime(), formatter);

        if(isAvailable(LocalDateTime.parse(request.getStartTime()))){
            reservation.setPetBarber(petBarber);
            reservation.setPet(pet);
            reservation.setStartTime(LocalDateTime.parse(request.getStartTime()));
            return reservationRepository.save(reservation);

        }else {
            throw new TimeSlotTakenException("The time slot is already taken.");
        }
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean isAvailable(LocalDateTime startTime) {
        List<Reservation> existingReservations = reservationRepository.findByStartTimeBetween(
                startTime, startTime.plusMinutes(30));
        return existingReservations.isEmpty();
    }

    public void isReservationExistsById(Long id){
        Optional<Reservation> optional = reservationRepository.findReservationById(id);
        if(optional.isEmpty()){
            throw new ReservationNotFoundException("Pet not found with ID: " + id);
        }
    }
}
