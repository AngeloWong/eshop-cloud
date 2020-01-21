package com.angelo.eshop.price.service.impl;

import com.angelo.eshop.price.mapper.ProductPriceMapper;
import com.angelo.eshop.price.model.ProductPrice;
import com.angelo.eshop.price.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceMapper productPriceMapper;

    @Override
    public void add(ProductPrice productPrice) {
        productPriceMapper.add(productPrice);
    }

    @Override
    public void update(ProductPrice productPrice) {
        productPriceMapper.update(productPrice);
    }

    @Override
    public void delete(Long id) {
        productPriceMapper.delete(id);
    }

    @Override
    public ProductPrice findById(Long id) {
        return productPriceMapper.findById(id);
    }

}
