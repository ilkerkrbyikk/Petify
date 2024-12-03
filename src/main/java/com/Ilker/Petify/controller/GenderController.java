package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.request.gender.AddGenderRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.GenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/gender")
@RequiredArgsConstructor
@Tag(name = "Gender")
public class GenderController {

    private final GenderService genderService;


    @Operation(
            summary = "Retrieves all genders.",
            description = "This endpoint returns a list of all genders available in the system. " +
                    "A successful response will include the list of genders."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Gender> genders = genderService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",genders));
    }

    @Operation(
            summary = "Adds a new gender.",
            description = "This endpoint allows the addition of a new gender to the system. " +
                    "The request must include the necessary details for the new gender. " +
                    "Upon successful addition, the newly created gender will be returned."
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid  @RequestBody AddGenderRequest request){
        Gender gender = genderService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",gender));
    }


    @Operation(
            summary = "Deletes a gender by its ID.",
            description = "This endpoint allows the deletion of a gender from the system using its ID. " +
                    "A successful deletion will return a success message."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@Valid @PathVariable Long id){
        genderService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.", null));
    }
}
