package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CarDamageCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarDamageGetResponse;
import com.erkvural.rentacar.dto.car.update.CarDamageUpdateRequest;
import com.erkvural.rentacar.entity.car.CarDamage;
import com.erkvural.rentacar.repository.car.CarDamageRepository;
import com.erkvural.rentacar.repository.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarDamageServiceImpl implements CarDamageService {
    private final CarDamageRepository repository;
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarDamageServiceImpl(CarDamageRepository repository, CarRepository carRepository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CarDamageCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(createRequest, CarDamage.class);
        this.repository.save(carDamage);

        return new SuccessResult(MessageStrings.DAMAGEADD);
    }

    @Override
    public DataResult<List<CarDamageGetResponse>> getAll() {
        List<CarDamage> result = repository.findAll();
        List<CarDamageGetResponse> response = result.stream()
                .map(carDamage -> modelMapperService.forResponse()
                        .map(carDamage, CarDamageGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.DAMAGELIST, response);
    }

    @Override
    public DataResult<CarDamageGetResponse> getById(long id) throws BusinessException {
        checkCarDamageIdExist(id);

        CarDamage carDamage = repository.getById(id);
        CarDamageGetResponse response = modelMapperService.forResponse().map(carDamage, CarDamageGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.DAMAGEFOUND, response);
    }

    @Override
    public Result update(long id, CarDamageUpdateRequest updateRequest) throws BusinessException {
        checkCarIdExist(updateRequest.getCarId());
        checkCarDamageIdExist(id);

        CarDamage carDamage = this.modelMapperService.forRequest().map(updateRequest, CarDamage.class);
        carDamage.setId(id);

        this.repository.save(carDamage);

        return new SuccessResult(MessageStrings.DAMAGEUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarDamageIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.DAMAGEDELETE);
    }

    private void checkCarDamageIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.DAMAGENOTFOUND);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(carId)))
            throw new BusinessException(MessageStrings.CARNOTFOUND);
    }
}
