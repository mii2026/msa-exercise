package com.sparta.msa_exam.product.poduct;

import com.sparta.msa_exam.product.core.Product;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long product_id;
    private String name;
    private Integer supply_price;

    public ProductResponseDto(Product product) {
        this.product_id = product.getProductId();
        this.name = product.getName();
        this.supply_price = product.getSupplyPrice();
    }
}
