package com.erkvural.rentacar.entity.car;

import com.erkvural.rentacar.entity.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "INVOICES")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "create_date")
    private LocalDate createDate = LocalDate.now();

    @Column(name = "rent_start_date")
    private LocalDate rentStartDate;

    @Column(name = "rent_end_date")
    private LocalDate rentEndDate;

    @Column(name = "total_rent_days")
    private Long totalRentDays;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;
}
