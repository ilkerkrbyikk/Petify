package com.Ilker.Petify.request.comment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddCommentRequest {


    @NotBlank(message = "Please set the entity type.")
    private String entityType;

    @NotBlank(message = "Rating is required.")
    @Min(value = 1,message = "You can give a minimum of 1 star.")
    @Max(value = 5, message = "You can give a maximum of 5 stars.")
    private int rating;

    @Size(min = 10, max = 500, message = "A comment must be 10-500 characters.")
    private String commentText;

    private Long petBarberId;
    private Long hotelId;
    private Long petSitterId;
}

