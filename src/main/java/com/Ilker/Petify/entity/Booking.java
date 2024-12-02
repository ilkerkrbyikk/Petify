package com.Ilker.Petify.entity;

import com.Ilker.Petify.dto.BookingDto;
import com.Ilker.Petify.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Check in date can not be null.")
    private LocalDate checkInDate;

    @NotNull(message = "Check out date can not be null.")
    private LocalDate checkOutDate;
    private double price;
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "hotel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "pet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pet pet;

    public BookingDto getBookingDto() {
        BookingDto dto = new BookingDto();

//        dto.setId(id);
        dto.setCheckInDate(checkInDate);
        dto.setCheckOutDate(checkOutDate);
        dto.setPetId(pet.getId());
        dto.setHotelId(hotel.getId());
        dto.setCustomerId(customer.getId());


        return dto;
    }
}
