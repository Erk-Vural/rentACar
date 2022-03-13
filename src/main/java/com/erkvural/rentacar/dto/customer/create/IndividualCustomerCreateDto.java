package com.erkvural.rentacar.dto.customer.create;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class IndividualCustomerCreateDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 64)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 64)
    private String lastName;
}
