package com.tui.pilotes.order;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<OrderModel, Integer> {

    @Query("SELECT number,date_time,client_id,product_id,quantity,status FROM orders o WHERE o.client_id=:clientId")
    Flux<OrderModel> findAllByClientId(Integer clientId);

    @Query("INSERT INTO orders (client_id,product_id,quantity) VALUES (:clientId, :productId, :quantity)")
    Mono<OrderModel> saveOrder(Integer clientId, Integer productId, Integer quantity);

    @Query("UPDATE orders SET quantity=:quantity WHERE number=:number AND (DATEDIFF('MINUTE', date_time0, LOCAL_TIME) < 5)")
    Mono<OrderModel> updateOrder(Integer number, Integer quantity);
}