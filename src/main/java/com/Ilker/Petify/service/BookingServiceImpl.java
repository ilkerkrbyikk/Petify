package com.Ilker.Petify.service;

import com.Ilker.Petify.dto.BookingDto;
import com.Ilker.Petify.entity.Booking;
import com.Ilker.Petify.entity.Customer;
import com.Ilker.Petify.entity.Hotel;
import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.enums.Status;
import com.Ilker.Petify.exception.*;
import com.Ilker.Petify.repository.BookingRepository;
import com.Ilker.Petify.repository.CustomerRepository;
import com.Ilker.Petify.repository.HotelRepository;
import com.Ilker.Petify.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final HotelRepository hotelRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository, PetRepository petRepository, HotelRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    @Override
    public Booking bookHotel(BookingDto bookingDto){
        /*
            burada ek bir kontrol olarak check in check out dateleri kontrol ediliyor.
         */
        if(bookingDto.getCheckInDate().isAfter(bookingDto.getCheckOutDate()) ||
                bookingDto.getCheckInDate().isEqual(bookingDto.getCheckOutDate())){
            throw new InvalidBookingDateException("Check-in date must be before check-out date.");
        }
        Booking booking = new Booking();
        Pet pet = petRepository.findById(bookingDto.getPetId())
                .orElseThrow(()->
                        new PetNotFoundException("Pet not found with given ID: " + bookingDto.getPetId()));
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(()->
                        new CustomerNotFoundException("Customer not found with given ID: " + bookingDto.getCustomerId()));
        Hotel hotel = hotelRepository.findById(bookingDto.getHotelId())
                .orElseThrow(()->
                        new HotelNotFoundException("Hotel not found with given ID: "+ bookingDto.getHotelId()));

        if(hotel.getCapacity() >= hotel.getCurrentCapacity()){
//            booking.setId(booking.getId());
            booking.setPet(pet);
            booking.setCustomer(customer);
            booking.setHotel(hotel);
            booking.setCheckInDate(bookingDto.getCheckInDate());
            booking.setCheckOutDate(bookingDto.getCheckOutDate());
            booking.setStatus(Status.PENDING);
            hotel.setCurrentCapacity(hotel.getCapacity() + 1 );
            hotelRepository.save(hotel);

            Long days = ChronoUnit.DAYS.between(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
            booking.setPrice((hotel.getPrice() * days));

            return bookingRepository.save(booking);
        }
        else{
            hotel.setAvailable(false);
            throw new HotelFullException("Hotel capacity is full.");
        }
    }

    @Transactional
    @Override
    public Booking updateBook(BookingDto bookingDto, Long id){
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);

        Pet pet = petRepository.findById(bookingDto.getPetId())
                .orElseThrow(()->
                        new PetNotFoundException("Pet not found with given ID: " + bookingDto.getPetId()));
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(()->
                        new CustomerNotFoundException("Customer not found with given ID: " + bookingDto.getCustomerId()));
        Hotel hotel = hotelRepository.findById(bookingDto.getHotelId())
                .orElseThrow(()->
                        new HotelNotFoundException("Hotel not found with given ID: "+ bookingDto.getHotelId()));

        if(hotel.getCapacity() >= hotel.getCurrentCapacity()){
//            booking.setId(booking.getId());
            booking.setPet(pet);
            booking.setCustomer(customer);
            booking.setHotel(hotel);
            booking.setCheckInDate(bookingDto.getCheckInDate());
            booking.setCheckOutDate(bookingDto.getCheckOutDate());
            hotel.setCurrentCapacity(hotel.getCurrentCapacity() + 1 );
            hotelRepository.save(hotel);

            Long days = ChronoUnit.DAYS.between(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
            booking.setPrice((hotel.getPrice() * days));

            return bookingRepository.save(booking);
        }
        else{
            hotel.setAvailable(false);
            throw new HotelFullException("Hotel capacity is full.");
        }
    }

    @Transactional
    @Override
    public void cancel(Long id){
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.getHotel().setCurrentCapacity(booking.getHotel().getCurrentCapacity() - 1);
        bookingRepository.deleteById(id);
    }

    public void checkIsExistBookingById(Long id){
        Optional<Booking> optional = bookingRepository.findById(id);
        if(optional.isEmpty()){
            throw new BookingNotFoundException("Booking not found with given ID: " + id);
        }
    }

    //TODO: CONTROLLER KISMINI YAZ
    @Override
    @Transactional
    public void statusApprove(Long id){
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.setStatus(Status.APPROVE);
        bookingRepository.save(booking);
    }

    //TODO: CONTROLLER KISMINI YAZ
    @Override
    @Transactional
    public void statusCancel(Long id){
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.setStatus(Status.CANCEL);
        bookingRepository.save(booking);
    }




}
