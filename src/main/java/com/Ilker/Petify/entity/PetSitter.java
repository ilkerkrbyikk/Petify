package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    private String firstName;
    private String lastName;
    private int age;
    @Column(name = "tc_no", length = 11, nullable = false)
    private String tckNo;
    private String phoneNumber;
    private boolean available;

    private Roles roles = Roles.PET_SITTER;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "petSitter")
    private List<Comment> comment;


}
