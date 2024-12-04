package com.Ilker.Petify.request.announcement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnnouncementRequest {

    @NotBlank(message = "Please enter some information.")
    private String description;

    @NotNull(message = "Please choose the pet.")
    private Long petId;
    private Long customerId;

}

