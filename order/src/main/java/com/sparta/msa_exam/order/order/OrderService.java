package com.sparta.msa_exam.order.order;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    OrderResponseDto updateOrder(Long orderId, Long productId);
    OrderResponseDto getOrder(Long orderId);
}
