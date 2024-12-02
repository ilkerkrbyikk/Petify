package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petSitters")
@PrimaryKeyJoinColumn(name = "sitter_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class PetSitter extends User {

    @NotNull(message = "Name field can not be null.")
    @Size(min = 2, max = 15, message = "Name field can be 3-15 characters.")
    private String firstName;

    @NotNull(message = "Lastname field can not be null.")
    @Size(min = 2, max = 15, message = "Lastname field can be 3-15 characters.")
    private String lastName;

    @NotNull(message = "Age field can not be null.")
    @Min(value = 18, message = "Under age people can not be pet sitter.")
    private int age;

    @Column(name = "tc_no", length = 11, nullable = false)
    @NotNull(message = "TCK no can not be null")
    @Size(min = 11, max = 11, message = "National number must be exactly 11 digits.")
    private String tckNo;


    @NotNull(message = "Phone number can not be null and it must start with +905...")
    @Size(min = 12, max = 12, message = "Phone number must start with (+905..) and it must be exactly 12 digits.")
    private String phoneNumber;

    @NotNull(message = "Availability can not be null.")
    private boolean available;

    private Roles roles = Roles.PET_SITTER;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull(message = "Please set the location of your city.")
    private City city;

    @OneToMany(mappedBy = "petSitter")
    private List<Comment> comment;


}
