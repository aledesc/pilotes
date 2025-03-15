package com.tui.pilotes.product;


import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class Product {

    @Id
    private Integer id;

    private String name;
    private Double price;

    public Product(Integer id) {
        this.id = id;
    }
}
