package com.sparta.msa_exam.product.poduct;

import com.sparta.msa_exam.product.core.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequestDto requestDto) {
        Product product = Product.builder()
                .name(requestDto.getName())
                .supplyPrice(requestDto.getSupply_price())
                .build();

        return productRepository.save(product);
    }

    public List<ProductResponseDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }
}
