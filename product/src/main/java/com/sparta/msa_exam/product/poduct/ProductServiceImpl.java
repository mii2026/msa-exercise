package com.sparta.msa_exam.product.poduct;

import com.sparta.msa_exam.product.core.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @CachePut(cacheNames = "productCache", key = "#result.product_id")
    @CacheEvict(cacheNames = "productAllCache", allEntries = true)
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = Product.builder()
                .name(requestDto.getName())
                .supplyPrice(requestDto.getSupply_price())
                .build();

        return new ProductResponseDto(productRepository.save(product));
    }

    @Override
    @Cacheable(cacheNames = "productAllCache")
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    @Override
    @Cacheable(cacheNames = "productCache", key = "args[0]")
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found or has been deleted"));
        return new ProductResponseDto(product);
    }
}
