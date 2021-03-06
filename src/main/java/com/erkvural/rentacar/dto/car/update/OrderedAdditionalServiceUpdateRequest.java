package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderedAdditionalServiceUpdateRequest {

    @NotNull
    @PositiveOrZero
    private long additionalServiceId;

    @Positive
    @Min(1)
    private int quantity;

    @NotNull
    @Positive
    private long carRentalId;

}
