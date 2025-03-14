package com.tui.pilotes.address;

import com.tui.pilotes.order.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Integer> {

}
