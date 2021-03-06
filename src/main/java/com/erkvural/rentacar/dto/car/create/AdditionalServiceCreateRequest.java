package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AdditionalServiceCreateRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private double dailyPrice;
}
