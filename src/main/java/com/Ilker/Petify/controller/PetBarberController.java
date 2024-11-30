package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.request.barber.AddPetBarberRequest;
import com.Ilker.Petify.request.barber.UpdatePetBarberRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.PetBarberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/c-customer/pet-barber")
@RequiredArgsConstructor
@Tag(name = "Pet Barber")
public class PetBarberController {

    private final PetBarberService petBarberService;


    @Operation(
            summary = "Retrieves a list of all pet barbers.",
            description = "This endpoint returns a list of all pet barbers available in the system. " +
                    "A successful response will include the details of all pet barbers."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<PetBarber> barberList = petBarberService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",barberList));
    }

    @Operation(
            summary = "Retrieves pet barbers by city.",
            description = "This endpoint returns a list of pet barbers located in the specified city. " +
                    "The city is identified by its ID. A successful response will include the details of the pet barbers in that city."
    )
    @GetMapping("/by-city/{cityId}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long cityId){
        List<PetBarber> barberList = petBarberService.getByCity(cityId);
        return ResponseEntity.ok(new ApiResponse("Success.",barberList));
    }

    @Operation(
            summary = "Adds a new pet barber.",
            description = "This endpoint allows the addition of a new pet barber to the system. " +
                    "The request must include the necessary details for the new pet barber. " +
                    "Upon successful addition, the newly created pet barber will be returned."
    )
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddPetBarberRequest request){
        PetBarber petBarber = petBarberService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",petBarber));
    }

    @Operation(
            summary = "Updates an existing pet barber.",
            description = "This endpoint allows the update of an existing pet barber's details. " +
                    "The request must include the updated details and the ID of the pet barber to be updated. " +
                    "Upon successful update, the updated pet barber will be returned."
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdatePetBarberRequest request, @PathVariable Long id){
        PetBarber petBarber = petBarberService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",petBarber));
    }

    @Operation(
            summary = "Deletes a pet barber by its ID.",
            description = "This endpoint allows the deletion of a pet barber from the system using its ID. " +
                    "A successful deletion will return a success message."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        petBarberService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success",null));
    }



}