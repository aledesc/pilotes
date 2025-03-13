package com.tui.pilotes.order;

import com.tui.pilotes.client.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class Order {
    @Id
    private String number;

    @Column(value = "date_time")
    private LocalDateTime dateTime;

    @Column(value = "client_id")
    private Integer clientId;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    @Column(value = "unitary_price")
    private Double unitaryPrice;

    @Column(value = "delivery_address")
    private Address deliveryAddress;

    private int status;

    public Order(Integer clientId, Integer productId, Integer quantity, Integer deliveryAddress){
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;

        this.deliveryAddress = new Address();
        this.deliveryAddress.setId(deliveryAddress);
    }

    public Order(Order order){
        this.number = order.getNumber();
        this.dateTime = order.getDateTime();
        this.clientId = order.getClientId();
        this.productId = order.getProductId();
        this.quantity = order.getQuantity();
        this.unitaryPrice = order.getUnitaryPrice();
        this.deliveryAddress = order.getDeliveryAddress();
        this.status = order.getStatus();

    }
}