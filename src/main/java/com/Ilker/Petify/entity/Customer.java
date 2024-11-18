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
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User {

    private String firstName;
    private String lastName;
    private String tckNo;
    private String phoneNumber;
    private int age;

    private Roles roles = Roles.CUSTOMER;
}

