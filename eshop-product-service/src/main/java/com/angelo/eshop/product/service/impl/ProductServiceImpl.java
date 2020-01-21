package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductMapper;
import com.angelo.eshop.product.model.Product;
import com.angelo.eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void add(Product product) {
        productMapper.add(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

}
