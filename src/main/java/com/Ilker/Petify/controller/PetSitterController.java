package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.PetSitter;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CommentService;
import com.Ilker.Petify.service.PetSitterService;
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
public class PetSitterController {

    private final PetSitterService petSitterService;
    private final CommentService commentService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<PetSitter> petSitters = petSitterService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",petSitters));
    }

    @GetMapping("/by-city/{cityId}")
    public ResponseEntity<ApiResponse> getByCity(@PathVariable Long cityId){
        Optional<List<PetSitter>> petSitters = petSitterService.getByCity(cityId);
        return ResponseEntity.ok(new ApiResponse("Success.",petSitters));
    }


    @GetMapping("/comments/{petSitterId}/averageRating")
    public ResponseEntity<ApiResponse> getAverageRating(@PathVariable Long id){
        PetSitter petSitter = new PetSitter();
        petSitter.setId(id);
        double averageRating = commentService.calculatePetSitterAverageRating(petSitter);
        return ResponseEntity.ok(new ApiResponse("Success.", averageRating));
    }

}