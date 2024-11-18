package com.Ilker.Petify.service;

import com.Ilker.Petify.dto.BookingDto;
import com.Ilker.Petify.entity.Booking;

public interface BookingService {

    //    List<Booking> getAll();
    Booking bookHotel(BookingDto bookingDto);
    void cancel(Long id);
    Booking updateBook(BookingDto bookingDto, Long id);

}