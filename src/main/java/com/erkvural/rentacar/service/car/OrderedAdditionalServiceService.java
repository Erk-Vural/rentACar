package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.dto.car.create.OrderedAdditionalServiceCreateRequest;
import com.erkvural.rentacar.entity.car.OrderedAdditionalService;

import java.util.Set;

public interface OrderedAdditionalServiceService {

    void add(Set<OrderedAdditionalServiceCreateRequest> createRequestSet, long carRentalId);

    Set<OrderedAdditionalService> getByCarRentalId(long carRentalId);

    Double calDailyTotal(Set<OrderedAdditionalService> orderedAdditionalServices);
}
