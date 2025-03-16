package com.tui.pilotes.product;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    @Override
    @Query("SELECT id, name, CAST(round(price,2) AS DECIMAL(6, 2)) as price FROM product")
    Flux<Product> findAll();

    @Query("SELECT id, name, CAST(round(price,2) AS DECIMAL(6, 2)) as price FROM product WHERE id=:productId")
    Mono<Product> findByProductId(Integer productId);
}