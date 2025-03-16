package com.tui.pilotes.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Component
public class ClientHandler {

    private final ClientService clientSrv;

    @Autowired
    public ClientHandler(ClientService srv) {
        this.clientSrv = srv;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {

        Flux<Client> clients= clientSrv.getAll();

        return clients
                .collectList()
                .flatMap(c -> {
                    if (c.isEmpty()) {
                        return ServerResponse.status(HttpStatus.NO_CONTENT).build();
                    } else {
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(clients, Client.class);
                    }
                });
    }

    public Mono<ServerResponse> get(ServerRequest request) {

        Integer clientId = Integer.parseInt(request.pathVariable("id"));
        Mono<Client> client = clientSrv.get(clientId);

        return client.flatMap(c -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(c))
                .switchIfEmpty(notFound().build());
    }
}