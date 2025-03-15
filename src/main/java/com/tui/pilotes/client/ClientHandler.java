package com.tui.pilotes.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ClientHandler {

    private final ClientService clientSrv;

    @Autowired
    public ClientHandler(ClientService srv) {
        this.clientSrv = srv;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {

        Flux<Client> client = clientSrv.getAll();
        return ServerResponse.ok().body(client, Client.class);
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