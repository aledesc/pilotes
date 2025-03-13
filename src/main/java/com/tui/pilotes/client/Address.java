package com.tui.pilotes.client;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    private Integer id;
    private String description;
    private int clientId;

    private String street;
    private String number;
    private String postCode;
    private String city;
    private String country;

}
