package com.tui.pilotes.address;

import com.tui.pilotes.order.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Integer> {

    Mono<Address> findAddressesByClientId(Integer id);
}
