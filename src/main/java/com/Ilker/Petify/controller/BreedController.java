package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.request.breed.AddBreedRequest;
import com.Ilker.Petify.request.breed.UpdateBreedRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/breed")
@RequiredArgsConstructor
public class BreedController {

    private final BreedService breedService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Breed> breeds = breedService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success", breeds));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddBreedRequest request){
        Breed breed = breedService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success", breed ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateBreedRequest request, @PathVariable(value = "id") Long id){
        Breed breed = breedService.update(request,id);
        return  ResponseEntity.ok(new ApiResponse("Success.", breed));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        breedService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success", null));
    }
}
