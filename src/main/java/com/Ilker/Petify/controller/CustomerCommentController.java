package com.Ilker.Petify.controller;

import com.Ilker.Petify.request.comment.AddCommentRequest;
import com.Ilker.Petify.response.ApiResponse;
import com.Ilker.Petify.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/customer/{customerId}/comment")
@RequiredArgsConstructor
@Tag(name = "Comment ")
public class CustomerCommentController {

    private final CommentService commentService;


    @Operation(
            summary = "Adds a comment to a specified entity.",
            description = "This endpoint allows a customer to add a comment to a specific entity type, which can be a hotel, pet barber, or pet sitter. " +
                    "The request must include the entity type and the corresponding ID of the entity to which the comment is being added. " +
                    "If the entity type is invalid, a bad request response will be returned."
    )
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
