package com.tui.pilotes.order;

import com.tui.pilotes.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class OrderModel {

    @Id
    private Integer number;

    @Column(value = "date_time")
    private LocalDateTime dateTime;

    @Column(value = "client_id")
    private Integer clientId;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    private Integer status;

}