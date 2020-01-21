package com.angelo.eshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductProperty {

    private Long id;
    private String name;
    private String value;
    private Long productId;
}
