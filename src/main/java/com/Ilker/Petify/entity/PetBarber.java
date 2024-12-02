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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetBarber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name can not be null.")
    @Size(min = 2, max = 20, message = "Name must be 2-15 characters.")
    private String name;

    @NotNull(message = "Price can not be null.")
    private double price;

    @NotNull(message = "Address can not be null.")
    @Size(min = 2, max = 50, message = "Address must be 2-50 characters.")
    private String address;

    @NotNull(message = "Phone number can not be null.")
    @Size(min = 12, max = 12, message = "Phone number must be exactly 12 digits.")
    private String phoneNumber;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id")
    @NotNull(message = "Please set the barber's location.")
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

