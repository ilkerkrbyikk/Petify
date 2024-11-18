package com.Ilker.Petify.request.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnnouncementRequest {

    private String description;
    private Long petId;
    private Long customerId;
}

