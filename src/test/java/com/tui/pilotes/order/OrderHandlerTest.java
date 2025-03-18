package com.tui.pilotes.order;

import com.tui.pilotes.client.Client;
import com.tui.pilotes.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.mockito.Mockito;
import reactor.core.publisher.Flux;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderHandlerTest {

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

    @Test
    void testGetOrderById() {

        Mockito.when(orderService.getOrdersByClientId(1)).thenReturn(Flux.just(order1, order2));

        webTestClient.get()
                .uri("/v1/order/{id}", "1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetOrdersByClientIdNoCon() {

        Mockito.when(orderService.getOrdersByClientId(-1024)).thenReturn(Flux.empty());

        webTestClient.get().uri("/v1/order/-1024")
                .exchange()
                .expectStatus().isOk();
    }






}