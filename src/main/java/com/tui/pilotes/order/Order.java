package com.tui.pilotes.order;

import com.tui.pilotes.address.InvalidAddressException;
import com.tui.pilotes.client.Client;
import com.tui.pilotes.product.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String number;
    private LocalDateTime dateTime;
    private Integer quantity;
    private Double unitaryPrice;
    private Double totalPrice;
    private Integer status;

    private Mono<Client> client;
    private Mono<Product> product;
}