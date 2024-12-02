package com.Ilker.Petify.entity;

import com.Ilker.Petify.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

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

    @Size(min = 10,max = 10, message = "Tax number must be exactly 10 digits.")
    @Pattern(regexp = "\\d{10}", message = "Tax number must contain only digits")
    private String taxNumber;

    @Size(max = 100, message = "Address can be max 100 digits.")
    private String address;
    private Roles roles = Roles.CORPORATE_CUSTOMER;

    @OneToMany(mappedBy = "corporateCustomer")
    private List<Hotel> hotels;

    @OneToMany(mappedBy = "corporateCustomer")
    private List<PetBarber> petBarbers;
}
