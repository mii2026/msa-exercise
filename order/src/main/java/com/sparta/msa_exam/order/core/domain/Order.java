package com.sparta.msa_exam.order.core.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String name;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> productIds;

    @Builder
    public Order(String name) {
        this.name = name;
    }

    public void setProductIds(List<OrderProduct> productIds) {
        this.productIds = productIds;
    }
}
