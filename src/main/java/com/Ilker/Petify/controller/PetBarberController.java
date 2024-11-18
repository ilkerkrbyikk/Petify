package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.PetBarber;
import com.Ilker.Petify.request.barber.AddPetBarberRequest;
import com.Ilker.Petify.request.barber.UpdatePetBarberRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.PetBarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/c-customer/pet-barber")
@RequiredArgsConstructor
public class PetBarberController {

    private final PetBarberService petBarberService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<PetBarber> barberList = petBarberService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",barberList));
    }

    @GetMapping("/by-city/{cityId}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long cityId){
        List<PetBarber> barberList = petBarberService.getByCity(cityId);
        return ResponseEntity.ok(new ApiResponse("Success.",barberList));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddPetBarberRequest request){
        PetBarber petBarber = petBarberService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",petBarber));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdatePetBarberRequest request, @PathVariable Long id){
        PetBarber petBarber = petBarberService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",petBarber));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        petBarberService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success",null));
    }



}