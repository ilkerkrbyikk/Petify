package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
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
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "corporate_customer_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class CorporateCustomer extends User{

    private String taxNumber;
    private String address;
    private Roles roles = Roles.CORPORATE_CUSTOMER;

    @OneToMany(mappedBy = "corporateCustomer")
    private List<Hotel> hotels;

    @OneToMany(mappedBy = "corporateCustomer")
    private List<PetBarber> petBarbers;
}
