package com.Ilker.Petify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private int capacity;
    private int currentCapacity = 0;
    private String description;
    private boolean available;
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "corporate_id")
    private CorporateCustomer corporateCustomer;

    @OneToMany(mappedBy = "hotel")
    private List<Comment> comment;
}

