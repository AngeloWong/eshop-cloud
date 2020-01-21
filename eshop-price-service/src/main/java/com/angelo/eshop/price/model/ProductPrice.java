package com.angelo.eshop.price.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {
	
	private Long id;
	private Double value;
	private Long productId;

}
