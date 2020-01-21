package com.angelo.eshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Long categoryId;
    private Long brandId;
}
