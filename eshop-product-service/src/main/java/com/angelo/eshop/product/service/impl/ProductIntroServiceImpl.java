package com.angelo.eshop.product.service.impl;


import com.angelo.eshop.product.mapper.ProductIntroMapper;
import com.angelo.eshop.product.model.ProductIntro;
import com.angelo.eshop.product.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

    @Autowired
    private ProductIntroMapper productIntroMapper;

    @Override
    public void add(ProductIntro productIntro) {
        productIntroMapper.add(productIntro);
    }

    @Override
    public void update(ProductIntro productIntro) {
        productIntroMapper.update(productIntro);
    }

    @Override
    public void delete(Long id) {
        productIntroMapper.delete(id);
    }

    @Override
    public ProductIntro findById(Long id) {
        return productIntroMapper.findById(id);
    }

}
