package com.tui.pilotes.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repo;

    public Mono<Order> findById(String id) {
        return repo.findById(id);
    }

    public Flux<Order> getByClientId(Integer id) {
        return repo.findByClient(id);
    }

    public Mono<Order> save(Order order) {
        return repo.save(order);
    }
}
