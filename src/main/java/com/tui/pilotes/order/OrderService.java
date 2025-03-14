package com.tui.pilotes.order;

import com.tui.pilotes.address.AddressRepository;
import com.tui.pilotes.client.Client;
import com.tui.pilotes.client.ClientRepository;
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
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, AddressRepository addressRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.addressRepository= addressRepository;
        this.productRepository= productRepository;
    }

    public Flux<String> getOrdersByClientId(Integer clientId) {
        return orderRepository.findAllByClientId(clientId)
                .flatMap(order ->
                clientRepository.findById(order.getClientId())
                        .zipWith(productRepository.findById(order.getProductId()))
                        .zipWith(addressRepository.findById(order.getProductId())
                        .map(tuple -> {
                            Client client = tuple.getT1();
                            Product product = tuple.getT2();
                            String details = "Order ID: " + orderId + "\n" +
                                    "Client: " + client.getName() + "\n" +
                                    "Product: " + product.getName() + "\n" +
                                    "Total Cost: $" + product.getPrice();
                            return details;
                        })
        );
    }
}
