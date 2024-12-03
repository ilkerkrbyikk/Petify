package com.Ilker.Petify.controller;

import com.Ilker.Petify.entity.ReHoming;
import com.Ilker.Petify.request.announcement.CreateAnnouncementRequest;
import com.Ilker.Petify.request.announcement.UpdateAnnouncementRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.ReHomingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rehoming")
@Tag(name = "Re-Homing")
public class ReHomingController {

    private final ReHomingService reHomingService;

    @Operation(
            summary = "Retrieves all re-homing announcements.",
            description = "This endpoint fetches a list of all re-homing announcements available in the system. " +
                    "The response will include details of each announcement, such as the pet's information, " +
                    "location, and contact details of the owner."
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<ReHoming> homingList = reHomingService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.", homingList));
    }

    //? @PreAuthorize at.
    @Operation(
            summary = "Creates a new re-homing announcement.",
            description = "This endpoint allows the creation of a new re-homing announcement. " +
                    "The request must include the necessary details in the request body. " +
                    "Upon successful creation, the newly created announcement will be returned."
    )
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateAnnouncementRequest request){
        ReHoming reHoming = reHomingService.add(request);
        return ResponseEntity.ok(new ApiResponse("Success.",reHoming));
    }

    //? @PreAuthorize at.
    @Operation(
            summary = "Updates an existing re-homing announcement.",
            description = "This endpoint allows updating an existing re-homing announcement identified by its ID. " +
                    "The request must include the updated details in the request body. " +
                    "Upon successful update, the updated announcement will be returned."
    )
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody UpdateAnnouncementRequest request,
                                              @PathVariable Long id ){
        ReHoming reHoming = reHomingService.update(request,id);
        return ResponseEntity.ok(new ApiResponse("Success.",reHoming));
    }

    //? @PreAuthorize at.
    @Operation(
            summary = "Deletes a re-homing announcement.",
            description = "This endpoint allows the deletion of a re-homing announcement identified by its ID. " +
                    "Upon successful deletion, a success message will be returned."
    )
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        reHomingService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Success.",null));
    }
}
