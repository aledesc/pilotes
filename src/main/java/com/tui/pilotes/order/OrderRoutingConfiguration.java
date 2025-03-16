package com.tui.pilotes.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        @RouterOperation(path = "/v1/order/{clientId}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = OrderHandler.class, beanMethod = "getClientOrders",
            operation = @Operation(operationId = "getClientOrders"
                    ,responses = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
                        @ApiResponse(responseCode = "204", description = "No conntent: There are not Orders from this Client", content = @Content(schema = @Schema(implementation = NoContent.class))),
                        @ApiResponse(responseCode = "401", description = "Authentication is required to get the requested response.")

                    }
                    ,parameters = { @Parameter(in = ParameterIn.PATH, name = "clientId") }
            )),
        @RouterOperation(path = "/v1/order", produces = {
                MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = OrderHandler.class, beanMethod = "create",
                operation = @Operation(operationId = "create"
                        , responses = {
                        @ApiResponse(responseCode = "201", description = "Order created!", content = @Content(schema = @Schema(implementation = Order.class))),
                        @ApiResponse(responseCode = "401", description = "Authentication is required to get the requested response.")}
                        , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrderModel.class)))
                )),

        @RouterOperation(path = "/v1/order", produces = {
                MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PATCH, beanClass = OrderHandler.class, beanMethod = "update",
                operation = @Operation(operationId = "update"
                        , responses = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
                        @ApiResponse(responseCode = "401", description = "Authentication is required to get the requested response.")}
                        , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrderModel.class)))
                )),

    })
    
    public RouterFunction<ServerResponse> orderRoutes(OrderHandler handler) {
        return route(GET("/v1/order/{clientId}"), handler::getClientOrders)
                .andRoute(POST("/v1/order"), handler::create)
                .andRoute(PATCH("/v1/order"), handler::update)
        ;
    }
}
