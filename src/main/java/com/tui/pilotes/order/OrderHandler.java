package com.tui.pilotes.order;

import com.tui.pilotes.address.InvalidAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.CoreSubscriber;
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

        Flux<String> orders = service.getOrdersByClientId(clientId);


        return ServerResponse.ok().body(orders, Order.class);
    }

    public Mono<ServerResponse> createClientOrder(ServerRequest request) {

        Integer clientId = Integer.parseInt(request.pathVariable("clientId"));
        Integer productId = Integer.parseInt(request.pathVariable("productId"));
        Integer many = Integer.parseInt(request.pathVariable("many"));
        Integer addressId = Integer.parseInt(request.pathVariable("addressId"));

        Mono<Order> order;
        try {
            OrderDTO o = new OrderDTO(clientId, productId, many, addressId);
            order = service.saveOrder(o.mapToOrder());

            return ServerResponse.ok().body(order, Order.class);

        } catch (InvalidAddressException e) {
            return getInvalidAddressServerResponse(e.getMessage());
        }
    }

    private Mono<ServerResponse> getInvalidAddressServerResponse(String exMsg) {
        Mono<String> msg = Mono.just(exMsg);
        return ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).body(BodyInserters.fromPublisher(msg, String.class));
    }

//    private Mono<ServerResponse> getNotFoundServerResponse() {
//        Mono<String> msg = Mono.just("Order not found!");
//        return ServerResponse.status(HttpStatus.NOT_FOUND).body(BodyInserters.fromPublisher(msg, String.class));
//    }
}