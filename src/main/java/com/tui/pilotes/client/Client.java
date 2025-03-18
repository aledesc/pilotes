package com.tui.pilotes.client;

import com.tui.pilotes.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Integer id;
    private String firstName;
    private String lastName;
    private String telephone;

    private Address address;

    public Client(Integer id) {
        this.id = id;
    }

    public Client(ClientModel dto, Address address) {
        this.id = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.telephone = dto.getTelephone();
        this.address = address;
    }
}
