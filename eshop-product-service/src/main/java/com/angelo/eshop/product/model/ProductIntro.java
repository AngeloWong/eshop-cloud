package com.angelo.eshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductIntro {

    private Long id;
    private String content;
    private Long productId;
}
