package com.erkvural.rentacar.dto.car.delete;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CityDeleteDto {
    @NotNull
    @Positive
    private long id;
}
