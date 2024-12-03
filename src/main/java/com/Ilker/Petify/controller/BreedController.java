package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.request.breed.AddBreedRequest;
import com.Ilker.Petify.request.breed.UpdateBreedRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.BreedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/breed")
@RequiredArgsConstructor
@Tag(name = "Breed")
public class BreedController {

    private final BreedService breedService;

    @Operation(
            summary = "Retrieve all breeds. Fetches a list of all available breeds."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Breed> breeds = breedService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success", breeds));
    }

    @Operation(
            summary = "Add a new breed. Creates a new breed with the provided details."
    )
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody AddBreedRequest request){
        Breed breed = breedService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success", breed ));
    }

    @Operation(
            summary = "Update an existing breed. Updates the details of an existing breed identified by its ID."
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody UpdateBreedRequest request, @PathVariable(value = "id") Long id){
        Breed breed = breedService.update(request,id);
        return  ResponseEntity.ok(new ApiResponse("Success.", breed));
    }

    @Operation(
            summary = "Deletes an existing breed."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@Valid @PathVariable Long id){
        breedService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success", null));
    }
}
