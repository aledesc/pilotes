package com.tui.pilotes.order;

import com.tui.pilotes.address.Address;
import com.tui.pilotes.address.AddressRepository;
import com.tui.pilotes.client.Client;
import com.tui.pilotes.client.ClientRepository;
import com.tui.pilotes.client.ClientService;
import com.tui.pilotes.product.Product;
import com.tui.pilotes.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientService clientService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productRepository= productRepository;
    }

    public Flux<Order> getOrdersByClientId(Integer clientId) {
        return orderRepository.findAllByClientId(clientId)
                .flatMap(order -> Mono
                        .zip(clientService.get(order.getClientId()),productRepository.findById(order.getProductId()))
                        .map(tuple -> new Order(order,tuple.getT1(),tuple.getT2()))
                );
    }

//    public Mono<Order> saveOrder(OrderModel order) {
//        return Mono.empty(); //orderRepository.save(order);
//    }
}
