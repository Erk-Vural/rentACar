package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarMaintenanceCreateRequest {

    @NotNull
    @Future
    private LocalDate returnDate;

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private long carId;
}
