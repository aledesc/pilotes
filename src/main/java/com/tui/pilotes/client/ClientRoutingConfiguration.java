package com.tui.pilotes.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRoutingConfiguration {

    @Bean
    @RouterOperations(
    {
        @RouterOperation(path = "/v1/client", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = ClientHandler.class, beanMethod = "getAll",
            operation = @Operation(operationId = "getAll"
                    ,responses = {
                        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Client.class))),
                        @ApiResponse(responseCode = "404", description = "Not found!")
                    }
            ))
    })

    public RouterFunction<ServerResponse> clientRoutes(ClientHandler handler) {
        return route(GET("/v1/client"), handler::getAll)
        ;
    }
}
