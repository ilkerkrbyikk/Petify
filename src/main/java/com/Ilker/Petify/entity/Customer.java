package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User {

    @NotNull(message = "Name can not be null.")
    @Size(min = 3, max = 15, message = "Name must be 3-15 characters.")
    private String firstName;

    @NotNull(message = "Name can not be null.")
    @Size(min = 3, max = 15, message = "Name must be 3-15 characters.")
    private String lastName;

    @NotNull(message = "TCK no can not be null")
    @Size(min = 11, max = 11, message = "National number must be exactly 11 digits.")
    private String tckNo;

    @NotNull(message = "Phone number can not be null and it must start with +905...")
    @Size(min = 12, max = 12, message = "Phone number must start with (+905..) and it must be exactly 12 digits.")
    private String phoneNumber;

    @NotNull(message = "Age can not be null.")
    @Min(value = 18, message = "You're underage. It's not allowed.")
    private int age;

    private Roles roles = Roles.CUSTOMER;
}

