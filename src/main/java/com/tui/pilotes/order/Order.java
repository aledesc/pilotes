package com.tui.pilotes.order;

import com.tui.pilotes.client.Client;
import com.tui.pilotes.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer number;
    private LocalDateTime dateTime;
    private Integer status;
    private Client client;

    private Integer quantity;
    private Product product;


    public Order(OrderModel orderModel, Client client) {
        this.number = orderModel.getNumber();
        this.dateTime = orderModel.getDateTime();
        this.quantity = orderModel.getQuantity();
        this.status = orderModel.getStatus();
        this.client = client;
    }

    public Order(OrderModel orderModel, Client client, Product product) {
        this.number = orderModel.getNumber();
        this.dateTime = orderModel.getDateTime();
        this.quantity = orderModel.getQuantity();
        this.status = orderModel.getStatus();
        this.client = client;
        this.product = product;
    }

    public Double getTotalPrice() {
        if(product==null) {
            return 0.0d;
        }
        return product.getPrice() * quantity;
    }
}