package com.tui.pilotes.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class OrderHandler {

    private final OrderService service;

    @Autowired
    public OrderHandler(OrderService srv) {
        this.service = srv;
    }

    public Mono<ServerResponse> getClientOrders(ServerRequest request) {

        Integer clientId = Integer.parseInt(request.pathVariable("clientId"));
        Flux<Order> Orders = service.getByClientId(clientId);

        return ServerResponse.ok().body(Orders, Order.class);
    }

    public Mono<ServerResponse> createClientOrder(ServerRequest request) {

        Integer clientId = Integer.parseInt(request.pathVariable("clientId"));
        Integer productId = Integer.parseInt(request.pathVariable("productId"));
        Integer many = Integer.parseInt(request.pathVariable("many"));
        Integer addressId = Integer.parseInt(request.pathVariable("addressId"));

        Order order = new Order(clientId,productId,many,addressId);
        Mono<Order> anorder = service.save(order);

        return ServerResponse.ok().body(anorder, Order.class);
    }


 
    private Mono<ServerResponse> getNotFoundServerResponse() {
        Mono<String> msg = Mono.just("Order not found!");
        return ServerResponse.status(HttpStatus.NOT_FOUND).body(BodyInserters.fromPublisher(msg, String.class));
    }
}