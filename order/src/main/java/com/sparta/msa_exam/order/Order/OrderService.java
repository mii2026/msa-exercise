package com.sparta.msa_exam.order.Order;

import com.sparta.msa_exam.order.core.client.ProductClient;
import com.sparta.msa_exam.order.core.client.ProductResponseDto;
import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.core.domain.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        for (Long productId : requestDto.getProduct_ids()) {
            ProductResponseDto product = productClient.getProduct(productId);
        }

        Order order = Order.builder()
                .name(requestDto.getName())
                .build();

        List<OrderProduct> products = new ArrayList<>();
        for(Long i: requestDto.getProduct_ids()){
            OrderProduct orderProduct = OrderProduct.builder()
                    .productId(i)
                    .order(order)
                    .build();
            products.add(orderProduct);
        }

        order = orderRepository.save(order);
        orderProductRepository.saveAll(products);
        order.setProductIds(products);
        return new OrderResponseDto(order);
    }

}
