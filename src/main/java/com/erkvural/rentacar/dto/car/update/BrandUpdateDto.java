package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BrandUpdateDto {

    @NotNull
    @NotBlank
    private String name;
}
