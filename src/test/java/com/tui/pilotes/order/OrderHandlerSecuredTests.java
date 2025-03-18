package com.tui.pilotes.order;

import com.tui.pilotes.client.Client;
import com.tui.pilotes.product.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@WebFluxTest(OrderHandler.class)
public class OrderHandlerSecuredTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private OrderService orderService;

    private Order order1;
    private Order order2;
    private OrderModel orderModel;
    private Client client;
    private Product product;


    @BeforeEach
    void setUp() {
        orderModel = new OrderModel(1, null,1,1,1,1);
        client= new Client(1);
        product= new Product(1,"Product",1.33);
        order1 = new Order(orderModel, client, product);
        order2 = new Order(orderModel, client, product);
    }

    @AfterEach
    void cleanUp() {
        orderModel = null;
        client= null;
        product= null;
        order1 = null;
        order2 = null;
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testSecuredSearchGets401StatusCode() {

        Mockito.when(orderService.search("field","accuracy","text")).thenReturn(Flux.just(order1, order2));

        webTestClient.get().uri("/v1/search/field/accuracy/text")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testSecuredSearchGetsNotFOund() {

        Mockito.when(orderService.search("field","accuracy","text")).thenReturn(Flux.just(order1, order2));

        webTestClient.get().uri("/v1/search/field/accuracy/text")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

}