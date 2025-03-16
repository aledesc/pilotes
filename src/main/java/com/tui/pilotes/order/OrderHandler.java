package com.tui.pilotes.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        Flux<Order> orders = service.getOrdersByClientId(clientId);

        return orders
                .collectList()
                .flatMap(o -> {
                    if (o.isEmpty()) {

                        Mono<NoContent> noContent= Mono.just(new NoContent(HttpStatus.NO_CONTENT, "There are Orders from this Client"));

                        return ServerResponse.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(noContent,NoContent.class));
                    } else {
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(orders, Order.class);
                    }
                });
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        Mono<OrderModel> dto= request.bodyToMono(OrderModel.class);
        return service.saveOrder(dto)
                .flatMap( o -> {
                    return ServerResponse.status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(o, Order.class);
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {

        Mono<OrderModel> dto= request.bodyToMono(OrderModel.class);
        return service.updateOrder(dto)
                .flatMap( o -> {
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(o, Order.class);
                        });
    }


}