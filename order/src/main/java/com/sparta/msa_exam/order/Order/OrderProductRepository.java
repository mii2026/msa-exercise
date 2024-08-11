package com.sparta.msa_exam.order.Order;

import com.sparta.msa_exam.order.core.domain.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
}
