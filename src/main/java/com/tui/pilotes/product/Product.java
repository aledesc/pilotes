package com.tui.pilotes.product;


import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Product {

    @Id
    private Integer id;
    private String name;
    private Double price;
}
