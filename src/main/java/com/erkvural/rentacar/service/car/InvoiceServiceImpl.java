package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.InvoiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;
import com.erkvural.rentacar.entity.car.Invoice;
import com.erkvural.rentacar.entity.car.Payment;
import com.erkvural.rentacar.entity.customer.Customer;
import com.erkvural.rentacar.repository.car.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService modelMapperService;
    private final PaymentService paymentService;
    private final CarRentalService carRentalService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repository, ModelMapperService modelMapperService, @Lazy PaymentService paymentService, CarRentalService carRentalService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.paymentService = paymentService;
        this.carRentalService = carRentalService;
    }

    @Override
    public Result add(InvoiceCreateRequest createRequest) {
        checkPaymentIdExist(createRequest.getPaymentId());

        Invoice invoice = setInvoice(createRequest.getPaymentId());

        this.repository.save(invoice);

        return new SuccessResult(MessageStrings.INVOICE_ADDED);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getAll() {
        List<Invoice> result = repository.findAll();

        List<InvoiceGetResponse> response = result.stream().map(invoice -> modelMapperService.forResponse().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.INVOICES_LISTED, response);
    }

    @Override
    public DataResult<InvoiceGetResponse> getById(long id) {
        checkInvoiceIdExist(id);

        Invoice invoice = repository.findById(id);
        InvoiceGetResponse response = modelMapperService.forResponse().map(invoice, InvoiceGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.INVOICE_FOUND, response);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getByCustomerId(long customerId) {
        List<Invoice> result = this.repository.findByCustomer_UserId(customerId);

        List<InvoiceGetResponse> response = result.stream().map(invoice -> this.modelMapperService.forResponse().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CUSTOMERS_INVOICES_LISTED, response);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getByBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = this.repository.findAllByRentStartDateLessThanEqualAndRentEndDateGreaterThanEqual(startDate, endDate);

        List<InvoiceGetResponse> response = result.stream().map(invoice -> this.modelMapperService.forResponse().map(invoice, InvoiceGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.INVOICES_LISTED, response);
    }

    private void checkPaymentIdExist(long paymentId) throws BusinessException {
        if (!Objects.nonNull(paymentService.getById(paymentId).getData()))
            throw new BusinessException(MessageStrings.PAYMENT_NOT_FOUND);
    }

    private void checkInvoiceIdExist(long id) throws BusinessException {
        if (!Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.INVOICE_NOT_FOUND);
    }

    private Invoice setInvoice(long paymentId) {

        PaymentGetResponse payment = this.paymentService.getById(paymentId).getData();
        CarRentalGetResponse carRental = this.carRentalService.getById(payment.getCarRentalId()).getData();

        Invoice invoice = new Invoice();

        invoice.setCreateDate(LocalDate.now());
        invoice.setRentStartDate(carRental.getStartDate());
        invoice.setRentEndDate(carRental.getEndDate());
        invoice.setTotalRentDays((ChronoUnit.DAYS.between(carRental.getStartDate(), carRental.getEndDate()) + 1));
        invoice.setTotal(payment.getTotal());
        Customer customer = new Customer();
        customer.setUserId(carRental.getCustomerId());
        invoice.setCustomer(customer);
        Payment payment1 = new Payment();
        payment1.setId(paymentId);
        invoice.setPayment(payment1);

        return invoice;
    }
}
