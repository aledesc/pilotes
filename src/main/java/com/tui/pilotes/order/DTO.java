package com.tui.pilotes.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Getter
@AllArgsConstructor
public class DTO {

    @Column(value = "client_id")
    private Integer clientId;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    @Column(value = "delivery_address")
    private Integer deliveryAddress;
}