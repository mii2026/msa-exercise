package com.sparta.msa_exam.product.poduct;

import com.sparta.msa_exam.product.core.domain.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponseDto implements Serializable {
    private Long product_id;
    private String name;
    private Integer supply_price;

    public ProductResponseDto(Product product) {
        this.product_id = product.getProductId();
        this.name = product.getName();
        this.supply_price = product.getSupplyPrice();
    }
}
