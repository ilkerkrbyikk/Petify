package com.Ilker.Petify.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private PetBarber petBarber;

    private double TotalPrice;

    public Reservation(Pet pet, LocalDateTime startTime, PetBarber petBarber) {
        this.pet = pet;
        this.petBarber = petBarber;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(30);
    }


}
