package com.Ilker.Petify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Max(5)
    private int rating;

    @Size(min = 10, max= 500, message = "Comment must have 10-250 characters.")
    private String commentText;
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "petBarberId", nullable = false)
    private PetBarber petBarber;


    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;


    @ManyToOne
    @JoinColumn(name = "petSitterId", nullable = false)
    private PetSitter petSitter;

}
