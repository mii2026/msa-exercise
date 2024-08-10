package com.sparta.msa_exam.product.poduct;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Value("${server.port}")
    private String serverPort;
    private final ProductService productService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Server-Port", serverPort);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto requestDto,
                                           HttpServletResponse response) {
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

}
