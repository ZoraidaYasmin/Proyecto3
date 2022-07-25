package com.proyecto1.customer.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
	private String id;
    private int indProduct;
    private String descIndProduct;
    private int typeProduct;
    private String descTypeProduct;
    private BigDecimal amountPerMonth;
    private BigDecimal amountPerDay;
}
