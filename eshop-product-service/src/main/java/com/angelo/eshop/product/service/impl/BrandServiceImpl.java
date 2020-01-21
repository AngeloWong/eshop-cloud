package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.BrandMapper;
import com.angelo.eshop.product.model.Brand;
import com.angelo.eshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void add(Brand brand) {
        brandMapper.add(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandMapper.delete(id);
    }

    @Override
    public Brand findById(Long id) {
        return brandMapper.findById(id);
    }

}
