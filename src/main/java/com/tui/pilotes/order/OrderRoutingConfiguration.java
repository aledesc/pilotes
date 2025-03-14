package com.tui.pilotes.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRoutingConfiguration {

    @Bean
    @RouterOperations(
    {
        @RouterOperation(path = "/v1/order/client/{clientId}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = OrderHandler.class, beanMethod = "getClientOrders",
            operation = @Operation(operationId = "getClientOrders"
                    ,responses = {
                        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
                        @ApiResponse(responseCode = "401", description = "Authentication is required to get the requested response."),
                        @ApiResponse(responseCode = "404", description = "Not found!")
                    }
                    ,parameters = { @Parameter(in = ParameterIn.PATH, name = "clientId") }
            )),
        @RouterOperation(path = "/v1/order/{clientId}/{productId}/{many}/{addressId}/", produces = {
                MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = OrderHandler.class, beanMethod = "createClientOrder",
                operation = @Operation(operationId = "createClientOrder"
                        , responses = {
                        @ApiResponse(responseCode = "201", description = "Order created!", content = @Content(schema = @Schema(implementation = Order.class))),
                        @ApiResponse(responseCode = "401", description = "Authentication is required to get the requested response.")}
                        ,parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "clientId"),
                            @Parameter(in = ParameterIn.PATH, name = "productId"),
                            @Parameter(in = ParameterIn.PATH, name = "many"),
                            @Parameter(in = ParameterIn.PATH, name = "addressId"),
                        }
                ))
    })
    public RouterFunction<ServerResponse> orderRoutes(OrderHandler handler) {
        return route(GET("/v1/order/client/{clientId}"), handler::getClientOrders)
//                .andRoute(POST("/v1/order/create/{clientId}/{productId}/{many}/{addressId}"), handler::createClientOrder)
        ;
    }
}
