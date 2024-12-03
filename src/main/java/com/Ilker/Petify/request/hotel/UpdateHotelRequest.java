package com.Ilker.Petify.request.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateHotelRequest {

    @NotBlank(message = "Name can not be null.")
    @Size(max = 45, message = "Name can be max 45 characters.")
    private String name;

    @NotBlank(message = "Phone number can not be null.")
    @Size(min = 12, max = 12, message = "Phone number must be exactly 12 digits.")
    private String phoneNumber;

    @NotBlank(message = "Capacity can not be null.")
    private int capacity;


    private String description;


    private boolean available;

    @NotNull(message = "You must specify the city of hotel.")
    private Long cityId;
    private Long corporateCustomerId;

    @NotBlank(message = "Price can not be null.")
    private double price;
}
