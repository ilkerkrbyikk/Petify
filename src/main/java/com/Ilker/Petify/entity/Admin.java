package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import jakarta.persistence.*;
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

    private String name;

    private Roles role = Roles.ADMIN;
}
