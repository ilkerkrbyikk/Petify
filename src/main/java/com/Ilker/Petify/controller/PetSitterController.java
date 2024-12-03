package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CommentService;
import com.Ilker.Petify.service.PetSitterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pet-sitter")
@RequiredArgsConstructor
@Tag(name = "Pet Sitter")
public class PetSitterController {

    private final PetSitterService petSitterService;
    private final CommentService commentService;


    @Operation(
            summary = "Retrieves all pet sitters.",
            description = "This endpoint fetches a list of all registered pet sitters in the system. " +
                    "The response will include the details of each pet sitter, such as their name, contact information, and availability."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<PetSitter> petSitters = petSitterService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",petSitters));
    }

    @Operation(
            summary = "Retrieves pet sitters by city.",
            description = "This endpoint fetches a list of pet sitters located in a specific city identified by the city ID. " +
                    "The response will include the details of each pet sitter available in that city."
    )
    @GetMapping("/by-city/{cityId}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long cityId){
        Optional<List<PetSitter>> petSitters = petSitterService.getByCity(cityId);
        return ResponseEntity.ok(new ApiResponse("Success.",petSitters));
    }


    @Operation(
            summary = "Calculates the average rating of a pet sitter.",
            description = "This endpoint calculates the average rating for a specific pet sitter identified by their ID." +
                    "Pet sitters can only be rated by customers." +
                    "The response will return the average rating based on the comments received for that pet sitter."
    )
    @GetMapping("/comments/{petSitterId}/averageRating")
    public ResponseEntity<ApiResponse> getAverageRating(@Valid @PathVariable Long id){
        PetSitter petSitter = new PetSitter();
        petSitter.setId(id);
        double averageRating = commentService.calculatePetSitterAverageRating(petSitter);
        return ResponseEntity.ok(new ApiResponse("Success.", averageRating));
    }

}