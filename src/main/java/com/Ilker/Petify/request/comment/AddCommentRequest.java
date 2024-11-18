package com.Ilker.Petify.request.comment;

import lombok.Data;

@Data
public class AddCommentRequest {

    private String entityType;
    private int rating;
    private String commentText;

    private Long petBarberId;

    private Long hotelId;
    private Long petSitterId;
}

