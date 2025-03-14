package com.tui.pilotes.order;

import com.tui.pilotes.address.InvalidAddressException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class Order {

    private String number;
    private LocalDateTime dateTime;

    private Integer clientId;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    @Column(value = "unitary_price")
    private Double unitaryPrice;

    @Column(value = "delivery_address")
    private Integer deliveryAddress;

    private Integer status;

    public Order(Integer clientId, Integer productId, Integer quantity, Integer addressId) throws InvalidAddressException {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.deliveryAddress = addressId;
    }

    public Order(Order order) throws InvalidAddressException {
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