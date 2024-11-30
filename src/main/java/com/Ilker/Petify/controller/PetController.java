package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.request.pet.AddPetRequest;
import com.Ilker.Petify.request.pet.UpdatePetRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/pet")
@RequiredArgsConstructor
@Tag(name = "Pet")
public class PetController {

    private final PetService petService;

    @Operation(
            summary = "Retrieves a list of all pets.",
            description = "This endpoint returns a list of all pets available in the system. " +
                    "A successful response will include the details of all pets."
    )

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Pet> pets = petService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", pets));
    }

    @Operation(
            summary = "Retrieves pets by gender.",
            description = "This endpoint returns a list of pets filtered by the specified gender ID. " +
                    "A successful response will include the details of the pets that match the gender."
    )
    @GetMapping("/by-gender/{id}")
    public ResponseEntity<ApiResponse> getByGender(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByGender(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @Operation(
            summary = "Retrieves pets by city.",
            description = "This endpoint returns a list of pets located in the specified city ID. " +
                    "A successful response will include the details of the pets in that city."
    )
    @GetMapping("/by-city/{id}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByCity(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @Operation(
            summary = "Retrieves pets by breed.",
            description = "This endpoint returns a list of pets filtered by the specified breed ID. " +
                    "A successful response will include the details of the pets that match the breed."
    )
    @GetMapping("/by-breed/{id}")
    public ResponseEntity<ApiResponse> getByBreed(@PathVariable Long id){
        Optional<List<Pet>> pets = petService.getByBreed(id);
        return ResponseEntity.ok(new ApiResponse("Success", pets));
    }

    @Operation(
            summary = "Adds a new pet.",
            description = "This endpoint allows the addition of a new pet to the system. " +
                    "The request must include the necessary details for the new pet. " +
                    "Upon successful addition, the newly created pet will be returned."
    )
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddPetRequest request
    ) throws IOException {
        Pet pet = petService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.", pet));
    }

    @Operation(
            summary = "Updates an existing pet.",
            description = "This endpoint allows the update of an existing pet's details. " +
                    "The request must include the updated details in the request body and the ID of the pet to be updated. " +
                    "Upon successful update, the updated pet will be returned."
    )
    @PutMapping("/pet/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdatePetRequest request, @PathVariable Long id){
        Pet pet = petService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",pet));
    }

    @Operation(
            summary = "Deletes a pet by its ID.",
            description = "This endpoint allows the deletion of a pet from the system using its ID. " +
                    "Upon successful deletion, a success message will be returned. " +
                    "If the pet with the specified ID does not exist, a 404 error will be returned."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        petService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
