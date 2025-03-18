package com.tui.pilotes.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class Common {

    public static Mono<ServerResponse> getNoContent() {
        Mono<NoContent> noContent = Mono.just(new NoContent(HttpStatus.NO_CONTENT, "No content"));
        return ServerResponse.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(noContent, NoContent.class));
    }

    public static Mono<ServerResponse> getNotFound() {
        Mono<NotFound> notFound = Mono.just(new NotFound(HttpStatus.NOT_FOUND, "Not found"));
        return ServerResponse.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(notFound, NotFound.class));
    }

}
