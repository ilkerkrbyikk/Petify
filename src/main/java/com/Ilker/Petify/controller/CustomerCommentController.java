package com.Ilker.Petify.controller;

import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/customer/{customerId}/comment")
@RequiredArgsConstructor
public class CustomerCommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request,
                                        @PathVariable Long id,
                                        @PathVariable Long customerId){
        switch (request.getEntityType()) {
            case "hotel":
                commentService.addHotelComment(request,id);
                break;
            case "petBarber":
                commentService.addPetBarberComment(request,id);
                break;
            case "petSitter":
                commentService.addPetSitterComment(request, id);
                break;

            default:
                return ResponseEntity.badRequest().body(new ApiResponse("Bad Request! Invalid entity type.",null));
        }
        return ResponseEntity.status(CREATED).body("Success.");
    }

}
