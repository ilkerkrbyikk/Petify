package com.Ilker.Petify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetBarber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String address;
    private String phoneNumber;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer corporateCustomer;

    @OneToMany(mappedBy = "petBarber")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "petBarber")
    private List<Comment> comment;



}

