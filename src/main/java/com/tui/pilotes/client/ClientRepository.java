package com.tui.pilotes.client;

import com.tui.pilotes.address.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Integer> {

}
