package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductPropertyMapper;
import com.angelo.eshop.product.model.ProductProperty;
import com.angelo.eshop.product.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    @Override
    public void add(ProductProperty productProperty) {
        productPropertyMapper.add(productProperty);
    }

    @Override
    public void update(ProductProperty productProperty) {
        productPropertyMapper.update(productProperty);
    }

    @Override
    public void delete(Long id) {
        productPropertyMapper.delete(id);
    }

    @Override
    public ProductProperty findById(Long id) {
        return productPropertyMapper.findById(id);
    }

}
