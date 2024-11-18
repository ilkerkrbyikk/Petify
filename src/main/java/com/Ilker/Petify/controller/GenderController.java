package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.Gender;
import com.Ilker.Petify.request.gender.AddGenderRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/gender")
@RequiredArgsConstructor
public class GenderController {

    private final GenderService genderService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Gender> genders = genderService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",genders));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddGenderRequest request){
        Gender gender = genderService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",gender));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        genderService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.", null));
    }
}
