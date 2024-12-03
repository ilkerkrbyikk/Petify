package com.Ilker.Petify.request.barber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddPetBarberRequest {

    @NotBlank(message = "Name can not be null.")
    @Size(min = 2, max = 20, message = "Name must be 2-15 characters.")
    private String name;

    @NotBlank(message = "Price can not be null.")
    private double price;

    @NotBlank(message = "Address can not be null.")
    @Size(min = 2, max = 60, message = "Address must be 2-60 characters.")
    private String address;

    @NotBlank(message = "Phone number can not be null.")
    @Size(min = 12, max = 12, message = "Phone number must be exactly 12 digits.")
    private String phoneNumber;

    @NotBlank(message = "Please set the corporate customer ID.")
    private Long corporateCustomerId;

    @NotBlank(message = "Please set the barber's location.")
    private Long cityId;
}