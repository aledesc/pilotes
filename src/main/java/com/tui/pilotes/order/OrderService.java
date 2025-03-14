package com.tui.pilotes.order;

import com.tui.pilotes.address.Address;
import com.tui.pilotes.address.AddressRepository;
import com.tui.pilotes.client.Client;
import com.tui.pilotes.client.ClientRepository;
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
    private final ClientRepository clientRepository;
//    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        //AddressRepository addressRepository
        //
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
//        this.addressRepository= addressRepository;
        this.productRepository= productRepository;
    }

    public Flux<String> getOrdersByClientId(Integer clientId) {
        return orderRepository.findAllByClientId(clientId)
                .flatMap(order ->
                clientRepository.findById(clientId)
                        .zipWith(productRepository.findById(order.getProduct().map(Product::getId)))
                        .map(tuple -> {
                            Client client = tuple.getT1();
                            Product product = tuple.getT2();
                            return "Order ID: " + order.getNumber() + "\n" +
                                    "Client: " + client.getFirstName() + " " + client.getLastName() + "\n" +
                                    "Product: " + product.getName() + " " + order.getQuantity() + "  " + product.getPrice() * order.getQuantity() + "€\n" +
                                    "Total Cost: $" + product.getPrice() * order.getQuantity() + " €\n----------------------------------------------\n" +
                                    "Deliver to: " + client.getAddress().map(Address::getStreet);
                        })
        );
    }

    public Mono<Order> saveOrder(Order order) {
        return orderRepository.save( order );
    }
}
