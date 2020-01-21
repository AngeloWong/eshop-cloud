package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductSpecificationMapper;
import com.angelo.eshop.product.model.ProductSpecification;
import com.angelo.eshop.product.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;

    @Override
    public void add(ProductSpecification productSpecification) {
        productSpecificationMapper.add(productSpecification);
    }

    @Override
    public void update(ProductSpecification productSpecification) {
        productSpecificationMapper.update(productSpecification);
    }

    @Override
    public void delete(Long id) {
        productSpecificationMapper.delete(id);
    }

    @Override
    public ProductSpecification findById(Long id) {
        return productSpecificationMapper.findById(id);
    }

}
