package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;

import java.util.List;

public interface ColorService {

    Result add(ColorCreateRequest createRequest);

    DataResult<List<ColorGetResponse>> getAll();

    DataResult<ColorGetResponse> getById(long id);

    Result update(long id, ColorUpdateRequest updateRequest);

    Result delete(long id);
}