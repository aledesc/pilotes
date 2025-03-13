package com.tui.pilotes.client;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@Getter
@NoArgsConstructor
public class Client {
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String telephone;
    private String email;

    private Set<Address> addresses;
}
