package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.request.pet.AddPetRequest;
import com.Ilker.Petify.request.pet.UpdatePetRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Pet> pets = petService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", pets));
    }

    @GetMapping("/by-gender/{id}")
    public ResponseEntity<ApiResponse> getByGender(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByGender(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @GetMapping("/by-city/{id}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByCity(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @GetMapping("/by-breed/{id}")
    public ResponseEntity<ApiResponse> getByBreed(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByBreed(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddPetRequest request
    ) throws IOException {
        Pet pet = petService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.", pet));
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdatePetRequest request, @PathVariable Long id){
        Pet pet = petService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",pet));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        petService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
