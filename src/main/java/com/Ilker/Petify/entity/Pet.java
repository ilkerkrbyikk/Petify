package com.Ilker.Petify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private int age;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "pet")
    private List<Reservation> reservations;


}