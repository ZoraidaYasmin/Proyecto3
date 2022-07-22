package com.proyecto1.deposit.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class Transaction {

    private String id;

    private String customerId;
    private String productId;
    private String accountNumber;
    private int movementLimit;
    private BigDecimal creditLimit;
    private BigDecimal availableBalance;
    private BigDecimal maintenanceCommission;
    private String cardNumber;
    private Integer maxAmountTransaction;
    private Integer currentNumberTransaction;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate retirementDateFixedTerm;

    @Transient
    private Customer customer;
    
    @Transient
    private Product product;



}
