package com.Ilker.Petify.response;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private int rating;
    private String commentText;

    private Long petBarberId;

    private Long hotelId;
    private Long petSitterId;
}
