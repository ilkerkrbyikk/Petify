package com.Ilker.Petify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Name can not be null.")
    @Size(min = 2, max = 20, message = "Name must be 2-15 characters.")
    private String name;

    @NotNull(message = "Weight can not be null.")
    private int weight;

    @NotNull(message = "Age can not be null.")
    private int age;

    @NotNull(message = "You must describe your pet.")
    @Size(max = 500, message = "Description can be maximum 500 characters.")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "breed_id")
    @NotNull(message = "Please set the pet's breed.")
    private Breed breed;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull(message = "Please set the pet's location.")
    private City city;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gender_id")
    @NotNull(message = "Please set the pet's gender.")
    private Gender gender;

    @OneToMany(mappedBy = "pet")
    private List<Reservation> reservations;


}