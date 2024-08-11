package com.sparta.msa_exam.product.poduct;

import java.util.List;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    List<ProductResponseDto> getProducts();
    ProductResponseDto getProduct(Long id);
}
