package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.request.city.AddCityRequest;
import com.Ilker.Petify.request.city.UpdateCityRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<City> cityList = cityService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success",cityList));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddCityRequest request){
        City city = cityService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.", city));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateCityRequest request, @PathVariable Long id){
        City city = cityService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",city));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        cityService.delete(id);

        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
