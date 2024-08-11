package com.sparta.msa_exam.order.core.client;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponseDto implements Serializable {
    private Long product_id;
    private String name;
    private Integer supply_price;
}