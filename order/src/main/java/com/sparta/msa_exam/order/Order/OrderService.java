package com.sparta.msa_exam.order.Order;

import com.sparta.msa_exam.order.core.client.ProductClient;
import com.sparta.msa_exam.order.core.client.ProductResponseDto;
import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.core.domain.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Set<Long> productIds = productClient.getProduct().stream()
                .map(ProductResponseDto::getProduct_id)
                .collect(Collectors.toSet());
        for (Long productId: requestDto.getProduct_ids()) {
            if(!productIds.contains(productId)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }
        }

        Order order = Order.builder()
                .name(requestDto.getName())
                .build();
        order = orderRepository.save(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for(Long i: requestDto.getProduct_ids()){
            OrderProduct orderProduct = OrderProduct.builder()
                    .productId(i)
                    .order(order)
                    .build();
            orderProducts.add(orderProduct);
        }
        orderProductRepository.saveAll(orderProducts);

        order.setProductIds(orderProducts);

        return new OrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found"));

        Set<Long> productIds = productClient.getProduct().stream()
                .map(ProductResponseDto::getProduct_id)
                .collect(Collectors.toSet());
        if(!productIds.contains(productId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        OrderProduct orderProduct = OrderProduct.builder()
                        .order(order)
                        .productId(productId)
                        .build();
        orderProductRepository.save(orderProduct);

        List<OrderProduct> orderProducts = order.getProductIds();
        orderProducts.add(orderProduct);
        order.setProductIds(orderProducts);

        return new OrderResponseDto(order);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found"));

        return new OrderResponseDto(order);
    }
}
