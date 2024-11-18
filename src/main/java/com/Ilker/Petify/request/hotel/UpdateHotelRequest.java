package com.Ilker.Petify.request.hotel;

import lombok.Data;

@Data
public class UpdateHotelRequest {

    private String name;
    private String phoneNumber;
    private int capacity;
    private String description;
    private boolean available;
    private Long cityId;
    private Long corporateCustomerId;
    private double price;
}
