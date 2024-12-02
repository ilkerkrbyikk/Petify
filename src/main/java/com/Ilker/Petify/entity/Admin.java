package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "admin_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Admin extends User {

    @NotNull(message = "Name can not be null.")
    @Size(min = 2, max = 15, message = "Name should be between 3-15 characters.")
    private String name;

    private Roles role = Roles.ADMIN;
}
