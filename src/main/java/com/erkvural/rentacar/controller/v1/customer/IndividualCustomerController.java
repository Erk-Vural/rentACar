package com.erkvural.rentacar.controller.v1.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateRequest;
import com.erkvural.rentacar.service.customer.IndividualCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/individuals")
public class IndividualCustomerController {
    private final IndividualCustomerService service;

    @Autowired
    public IndividualCustomerController(IndividualCustomerService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody  @Valid IndividualCustomerCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<IndividualCustomerGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<IndividualCustomerGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody  @Valid  IndividualCustomerUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
