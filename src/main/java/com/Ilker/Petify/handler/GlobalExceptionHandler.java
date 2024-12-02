package com.Ilker.Petify.handler;

import com.Ilker.Petify.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    //* VALIDATION EXCEPTION
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    //* DIGER VALIDATION EXCEPTION
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AdminAlreadyExists.class})
    public ResponseEntity<Object> handleAdminAlreadyExistsException(AdminAlreadyExists e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({AdminNotFoundException.class})
    public ResponseEntity<Object> handleAdminNotFoundException(AdminNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }


    @ExceptionHandler({AnnouncementNotFoundException.class})
    public ResponseEntity<Object> handleAnnouncementNotFoundException(AnnouncementNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }


    @ExceptionHandler({BookingNotFoundException.class})
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({BreedAlreadyExistException.class})
    public ResponseEntity<Object> handleBreedAlreadyExistsException(BreedAlreadyExistException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({BreedNotExistsException.class})
    public ResponseEntity<Object> handleBreedNotExistsException(BreedNotExistsException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CityAlreadyExistsException.class})
    public ResponseEntity<Object> handleCityAlreadyExistsException(CityAlreadyExistsException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CityNotFoundException.class})
    public ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CorporateCustomerAlreadyExistsException.class})
    public ResponseEntity<Object> handleCorporateCustomerAlreadyExistsException(CorporateCustomerAlreadyExistsException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
    @ExceptionHandler({CorporateCustomerNotFoundException.class})
    public ResponseEntity<Object> handleCorporateCustomerNotFoundException(CorporateCustomerNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CustomerAlreadyExistsException.class})
    public ResponseEntity<Object> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({HotelFullException.class})
    public ResponseEntity<Object> handleHotelFullException(HotelFullException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({HotelNotFoundException.class})
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({PetNotFoundException.class})
    public ResponseEntity<Object> handlePetNotFoundException(PetNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({PetSitterAlreadyExistsException.class})
    public ResponseEntity<Object> handlePetSitterAlreadyExistsException(PetSitterAlreadyExistsException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({PetSitterNotFoundException.class})
    public ResponseEntity<Object> handlePetSitterNotFoundException(PetSitterNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({PetBarberNotFoundException.class})
    public ResponseEntity<Object> handlePetBarberNotFoundException(PetBarberNotFoundException e){
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(TimeSlotTakenException.class)
    public ResponseEntity<Object> handleTimeSlotTakenException(TimeSlotTakenException e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }



}

