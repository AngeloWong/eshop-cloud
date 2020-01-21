package com.angelo.eshop.inventory.service.impl;

import com.angelo.eshop.inventory.mapper.ProductInventoryMapper;
import com.angelo.eshop.inventory.model.ProductInventory;
import com.angelo.eshop.inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryMapper;

    public void add(ProductInventory productInventory) {
        productInventoryMapper.add(productInventory);
    }

    public void update(ProductInventory productInventory) {
        productInventoryMapper.update(productInventory);
    }

    public void delete(Long id) {
        productInventoryMapper.delete(id);
    }

    public ProductInventory findById(Long id) {
        return productInventoryMapper.findById(id);
    }

}
