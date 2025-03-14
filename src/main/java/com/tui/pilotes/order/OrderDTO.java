package com.tui.pilotes.order;

import com.tui.pilotes.address.Address;
import com.tui.pilotes.address.InvalidAddressException;
import com.tui.pilotes.client.Client;
import com.tui.pilotes.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;
import reactor.core.publisher.Mono;

@Data
@Getter
@AllArgsConstructor
public class OrderDTO {

    @Column(value = "client_id")
    private Integer clientId;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    @Column(value = "delivery_address")
    private Integer deliveryAddress;

    public Order mapToOrder() throws InvalidAddressException {

        Order o= new Order();
        o.setQuantity( this.quantity );

        Product product= new Product( this.productId );
        o.setProduct(Mono.just(product));

        Address address= new Address( this.deliveryAddress);
        Client client= new Client( this.clientId, address );
        o.setClient(Mono.just(client));

        return o;
    }
}