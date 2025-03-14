package com.tui.pilotes.address;

import com.tui.pilotes.client.InvalidClienteIdException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;
import java.util.function.Predicate;

@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    private Integer id;

    @Column(value = "cliente_id")
    private Integer clientId;

    private String description;
    private String street;
    private String number;

    @Column(value = "postal_code")
    private String postalCode;

    private String city;
    private String country;

    private static Predicate<Address> isNotNullAddress = Objects::nonNull;
    private static Predicate<String> isNotEmptyString = StringUtils::isNotEmpty;

    private static Boolean  _isAValidAddress(Address address) {

        return isNotNullAddress.test(address) && (
                isNotEmptyString.test(address.getStreet()) &&
                        isNotEmptyString.test(address.getNumber()) &&
                        isNotEmptyString.test(address.getPostalCode()) &&
                        isNotEmptyString.test(address.getCity()) &&
                        isNotEmptyString.test(address.getCountry())
        );
    }

    private static Predicate<Address> isAValidAddress = Address::_isAValidAddress;
    public Address(Address address) throws InvalidAddressException {

        if( !isAValidAddress.test(address) ) {
            throw new InvalidAddressException();
        }
    }

    private static Predicate<Integer> isNotAValidAddressId = id -> ( id == null || id < 1 );
    public Address(Integer id)  throws InvalidAddressException {

        if ( isNotAValidAddressId.test(id) ) {
            throw new InvalidAddressException();
        }
        this.id = id;
    }

    public Address(String street, String number, String postCode, String city, String country) throws InvalidAddressException {

        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setPostalCode(postCode);
        address.setCity(city);
        address.setCountry(country);

        if( !isAValidAddress.test(address) ) {
            throw new InvalidAddressException();
        }

        this.street = street;
        this.number = number;
        this.postalCode = postCode;
        this.city = city;
        this.country = country;
    }

    private static Predicate<Integer> isNotAValidClienteId = id -> ( id == null || id < 1 );
    public Address(Integer clienteId, String street, String number, String postCode, String city, String country) throws InvalidAddressException, InvalidClienteIdException {

        this(street,number,postCode,city,country);

        if ( isNotAValidClienteId.test(clienteId) ) {
            throw new InvalidClienteIdException();
        }
        this.clientId = clienteId;
    }

}
