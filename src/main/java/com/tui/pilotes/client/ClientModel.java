package com.tui.pilotes.client;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@NoArgsConstructor
@Table("client")
public class ClientModel {
    @Id
    @Column(value = "id")
    private Integer id;

    @Column(value = "first_name")
    private String firstName;

    @Column(value = "last_name")
    private String lastName;

    private String telephone;
}
