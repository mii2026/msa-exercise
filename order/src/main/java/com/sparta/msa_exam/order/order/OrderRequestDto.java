package com.sparta.msa_exam.order.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private String name;
    private List<Long> product_ids;
}
