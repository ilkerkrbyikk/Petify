package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Booking findBookingById(Long id);
}
