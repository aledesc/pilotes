package com.tui.pilotes.order;

import com.tui.pilotes.client.ClientService;
import com.tui.pilotes.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientService clientService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productRepository= productRepository;
    }

    public Flux<Order> getOrdersByClientId(Integer clientId) {
        return orderRepository.findAllByClientId(clientId)
                .flatMap(order -> Mono
                        .zip(clientService.get(order.getClientId()),productRepository.findById(order.getProductId()))
                        .map(tuple -> new Order(order,tuple.getT1(),tuple.getT2()))
                );
    }

    public Mono<Order> saveOrder(@RequestBody Mono<OrderModel> order) {

        Mono<OrderModel> om= order.flatMap(o -> orderRepository.saveOrder(o.getClientId(), o.getProductId(), o.getQuantity()));

        return om.flatMap(o -> Mono
                .zip(clientService.get(o.getClientId()),productRepository.findById(o.getProductId()))
                .map(tuple -> new Order(o,tuple.getT1(),tuple.getT2()))
        );
    }

    public Mono<Order> updateOrder(@RequestBody Mono<OrderModel> order) {

        Mono<OrderModel> om= order.flatMap(o -> orderRepository.updateOrder(o.getNumber(), o.getQuantity()));

        return om.flatMap(o -> Mono
                .zip(clientService.get(o.getClientId()),productRepository.findById(o.getProductId()))
                .map(tuple -> new Order(o,tuple.getT1(),tuple.getT2()))
        );
    }

}
