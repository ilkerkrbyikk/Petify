package com.Ilker.Petify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Pet> pets;

    @OneToMany(mappedBy = "city")
    private List<PetSitter> petSitters;

    @OneToMany(mappedBy = "city")
    private List<Hotel> hotel;

    @OneToMany(mappedBy = "city")
    private List<PetBarber> barbers;
}
