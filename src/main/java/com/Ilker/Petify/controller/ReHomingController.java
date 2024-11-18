package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.ReHoming;
import com.Ilker.Petify.request.announcement.CreateAnnouncementRequest;
import com.Ilker.Petify.request.announcement.UpdateAnnouncementRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.ReHomingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rehoming")
public class ReHomingController {

    private final ReHomingService reHomingService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<ReHoming> homingList = reHomingService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", homingList));
    }

    //? @PreAuthorize at.
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateAnnouncementRequest request){
        ReHoming reHoming = reHomingService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",reHoming));
    }

    //? @PreAuthorize at.
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateAnnouncementRequest request,
                                              @PathVariable Long id ){
        ReHoming reHoming = reHomingService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",reHoming));
    }

    //? @PreAuthorize at.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        reHomingService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
