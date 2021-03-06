package com.erkvural.rentacar.entity.customer;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "INDIVIDUAL_CUSTOMERS")
public class IndividualCustomer extends Customer {

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;


    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "national_id", unique = true)
    private String nationalId;
}
