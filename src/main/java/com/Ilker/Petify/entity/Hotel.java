package com.Ilker.Petify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;
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

    @NotNull(message = "Name can not be null.")
    @Size(max = 45, message = "Name can be max 45 characters.")
    private String name;

    @NotNull(message = "Phone number can not be null.")
    @Size(min = 12, max = 12, message = "Phone number must be exactly 12 digits.")
    private String phoneNumber;

    @NotNull(message = "Capacity can not be null.")
    private int capacity;

    @NotNull(message = "Capacity can not be null.")
    private int currentCapacity = 0;
    private String description;
    private boolean available;

    @NotNull(message = "Price can not be null.")
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id")
    @NotNull(message = "You must specify the city of hotel.")
    private City city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "corporate_id")
    private CorporateCustomer corporateCustomer;

    @OneToMany(mappedBy = "hotel")
    private List<Comment> comment;

    @Lob
    @Column(name = "hotel_image", columnDefinition = "LONGBLOB")
    private byte[] hotelImage;

    private byte[] hotelProfilePic;


    //BASE64 ENCODING
    public String getBase64Image() {
        return hotelImage != null ?
                Base64.getEncoder().encodeToString(hotelImage) : null;
    }
}

