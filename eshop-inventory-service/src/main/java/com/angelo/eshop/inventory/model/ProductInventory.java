package com.angelo.eshop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory {
	
	private Long id;
	private Integer value;
	private Long productId;

}
