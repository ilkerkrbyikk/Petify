package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.request.city.AddCityRequest;
import com.Ilker.Petify.request.city.UpdateCityRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/city")
@RequiredArgsConstructor
@Tag(name = "City")
public class CityController {

    private final CityService cityService;


    @Operation(
            summary = "Fetches a list of all available cities."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<City> cityList = cityService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success",cityList));
    }

    @Operation(
            summary = "Creates a new city with the provided details."
    )
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody AddCityRequest request){
        City city = cityService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.", city));
    }

    @Operation(
            summary = "Updates the details of an existing city identified by its ID."
    )

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody UpdateCityRequest request, @PathVariable Long id){
        City city = cityService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",city));
    }

    @Operation(
            summary = "Deletes a city identified by its ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@Valid @PathVariable Long id){
        cityService.delete(id);

        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
