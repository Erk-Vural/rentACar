package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateRequest;
import com.erkvural.rentacar.dto.car.get.CityGetResponse;
import com.erkvural.rentacar.dto.car.update.CityUpdateRequest;
import com.erkvural.rentacar.service.car.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car/cities")
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CityCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @Cacheable("cities")
    @GetMapping("/get/all")
    public DataResult<List<CityGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CityGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody @Valid CityUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
