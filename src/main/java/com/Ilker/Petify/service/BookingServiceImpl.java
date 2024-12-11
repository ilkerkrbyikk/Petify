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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

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

        logger.info("Attempting to book hotel for pet ID: {} and customer ID: {}", bookingDto.getPetId(), bookingDto.getCustomerId());

        /*
            burada ek bir kontrol olarak check in check out dateleri kontrol ediliyor.
         */
        if(bookingDto.getCheckInDate().isAfter(bookingDto.getCheckOutDate()) ||
                bookingDto.getCheckInDate().isEqual(bookingDto.getCheckOutDate())){
            logger.error("Invalid booking dates: Check-in date must be before check-out date.");
            throw new InvalidBookingDateException("Check-in date must be before check-out date.");
        }
        Booking booking = new Booking();
        Pet pet = petRepository.findById(bookingDto.getPetId())
                .orElseThrow(() -> {
                    logger.error("Pet not found with ID: {}", bookingDto.getPetId());
                    return new PetNotFoundException("Pet not found with given ID: " + bookingDto.getPetId());
                });
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(() -> {
            logger.error("Customer not found with ID: {}", bookingDto.getCustomerId());
            return new CustomerNotFoundException("Customer not found with given ID: " + bookingDto.getCustomerId());
        });
        Hotel hotel = hotelRepository.findById(bookingDto.getHotelId())
                .orElseThrow(() -> {
                    logger.error("Hotel not found with ID: {}", bookingDto.getHotelId());
                    return new HotelNotFoundException("Hotel not found with given ID: " + bookingDto.getHotelId());
                });

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

            logger.info("Hotel booked successfully for pet ID: {} and customer ID: {}", bookingDto.getPetId(), bookingDto.getCustomerId());
            return bookingRepository.save(booking);
        }
        else{
            hotel.setAvailable(false);
            logger.error("Hotel capacity is full for hotel ID: {}", bookingDto.getHotelId());
            throw new HotelFullException("Hotel capacity is full.");
        }
    }

    @Transactional
    @Override
    public Booking updateBook(BookingDto bookingDto, Long id){
        logger.info("Attempting to update booking with ID: {}", id);
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);

        Pet pet = petRepository.findById(bookingDto.getPetId())
                .orElseThrow(() -> {
                    logger.error("(UpdateBookFunction)Pet not found with ID: {}", bookingDto.getPetId());
                    return new PetNotFoundException("Pet not found with given ID: " + bookingDto.getPetId());
                });
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(() -> {
                    logger.error("(UpdateBookFunction)Customer not found with ID: {}", bookingDto.getCustomerId());
                    return new CustomerNotFoundException("Customer not found with given ID: " + bookingDto.getCustomerId());
                });
        Hotel hotel = hotelRepository.findById(bookingDto.getHotelId())
                .orElseThrow(() -> {
                    logger.error("(UpdateBookFunction)Hotel not found with ID: {}", bookingDto.getHotelId());
                    return new HotelNotFoundException("Hotel not found with given ID: " + bookingDto.getHotelId());
                });

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

            logger.info("Booking with ID: {} updated successfully.", id);
            return bookingRepository.save(booking);
        }
        else{
            hotel.setAvailable(false);
            logger.error("(UpdateBookFunction)Hotel capacity is full for hotel ID: {}", bookingDto.getHotelId());
            throw new HotelFullException("Hotel capacity is full.");
        }
    }

    @Transactional
    @Override
    public void cancel(Long id){
        logger.info("Attempting to cancel booking with ID: {}", id);
        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.getHotel().setCurrentCapacity(booking.getHotel().getCurrentCapacity() - 1);
        bookingRepository.deleteById(id);

    }

    public void checkIsExistBookingById(Long id){
        Optional<Booking> optional = bookingRepository.findById(id);
        if(optional.isEmpty()){
            logger.error("Booking not found with ID: {}", id);
            throw new BookingNotFoundException("Booking not found with given ID: " + id);
        }
    }

    //TODO: CONTROLLER KISMINI YAZ
    @Override
    @Transactional
    public void statusApprove(Long id){
        logger.info("Approving booking with ID: {}", id);

        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.setStatus(Status.APPROVE);
        bookingRepository.save(booking);
        logger.info("Booking with ID: {} approved successfully.", id);

    }

    //TODO: CONTROLLER KISMINI YAZ
    @Override
    @Transactional
    public void statusCancel(Long id){
        logger.info("Canceling booking with ID: {}", id);

        checkIsExistBookingById(id);
        Booking booking = bookingRepository.findBookingById(id);
        booking.setStatus(Status.CANCEL);
        bookingRepository.save(booking);
        logger.info("(StatusCancelFunction) Booking with ID: {} canceled successfully.", id);

    }




}
