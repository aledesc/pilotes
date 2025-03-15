package com.tui.pilotes.client;

import com.tui.pilotes.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,AddressRepository addressRepository){
        this.clientRepository = clientRepository;
        this.addressRepository= addressRepository;
    }

    public Flux<Client> getAll() {
        return clientRepository.findAll()
                .flatMap(client -> addressRepository.findAddressesByClientId(client.getId())
                        .map( address -> new Client(client,address)));
    }

    public Mono<Client> get(Integer clientId) {
        return clientRepository.findById(clientId)
                .flatMap(client -> addressRepository.findAddressesByClientId(client.getId())
                .map( address -> new Client(client,address)));
    }


}
