package com.sparta.msa_exam.order.Order;

import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.core.domain.OrderProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto implements Serializable {
    private Long order_id;
    private List<Long> product_ids;

    public OrderResponseDto(Order order) {
        this.order_id = order.getOrderId();
        this.product_ids = new ArrayList<>();
        for(OrderProduct orderProduct: order.getProductIds()){
            this.product_ids.add(orderProduct.getProductId());
        }
    }
}
