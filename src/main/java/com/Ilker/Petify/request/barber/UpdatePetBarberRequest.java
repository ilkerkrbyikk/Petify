package com.Ilker.Petify.request.barber;


import lombok.Data;

@Data
public class UpdatePetBarberRequest {

    private String name;
    private double price;
    private String address;
    private String phoneNumber;

    private Long corporateCustomerId;
    private Long cityId;
}