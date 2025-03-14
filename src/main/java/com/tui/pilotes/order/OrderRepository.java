package com.tui.pilotes.order;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

    @Query("SELECT o.number,o.date_time,o.client_id,o.product_id,o.quantity,o.unit_price,o.delivery_address,o.status FROM orders o WHERE o.client_id=:clientId")
    Flux<Order> findAllByClientId(Integer clientId);
}