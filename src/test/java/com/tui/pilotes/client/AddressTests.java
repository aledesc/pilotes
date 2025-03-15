package com.tui.pilotes.client;

import com.tui.pilotes.address.Address;
import com.tui.pilotes.address.InvalidAddressException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTests {

    private Address address;
    private Integer clienteId;
    private String street;
    private String number;
    private String postCode;
    private String city;
    private String country;

    @BeforeEach
    public void setUp() {
        address = new Address();
    }

    @AfterEach
    public void tearDown() {
        address = null;
    }

    @Test
    void checkThrowingInvalidAddressException() {

        address= null;
        Exception exception = assertThrows(InvalidAddressException.class, () -> new Address(address));
//        exception = assertThrows(InvalidAddressException.class, () -> new Address(-1));
//        exception = assertThrows(InvalidAddressException.class, () -> new Address(0));

        street= "Elm Street";
        number= "1 D";
        postCode= "20009";
        city= "Elmore City";
        country= null;

        exception = assertThrows(InvalidAddressException.class, () -> new Address(street,number,postCode,city,country));

        street= "";
        exception = assertThrows(InvalidAddressException.class, () -> new Address(street,number,postCode,city,country));

        street= "Elm Street";
        country= "Spain";
        assertDoesNotThrow(()->new Address(street,number,postCode,city,country));

        clienteId= 1024;
        assertDoesNotThrow(()->new Address(clienteId,street,number,postCode,city,country));

        exception = assertThrows(InvalidClienteIdException.class, () -> new Address(0,street,number,postCode,city,country));
    }
}
