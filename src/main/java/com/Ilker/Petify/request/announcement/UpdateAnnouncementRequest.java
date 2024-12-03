package com.Ilker.Petify.request.announcement;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnnouncementRequest {

    @NotBlank(message = "Please enter some information.")
    private String description;

    @NotBlank(message = "Please choose the pet.")
    private Long petId;
    private Long customerId;
}

