package com.tui.pilotes.order;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository  extends ReactiveCrudRepository<Order, String> {

    @Query("select * from orders where client_id=:clientId")
    Flux<Order> findByClient(Integer clientId);
}