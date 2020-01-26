package com.angelo.eshop.product.service;


import com.angelo.eshop.product.model.Brand;

import java.util.List;

public interface BrandService {

    public void add(Brand brand, String operationType);

    public void update(Brand brand, String operationType);

    public void delete(Long id, String operationType);

    public Brand findById(Long id);

    public List<Brand> findByIds(String ids);

}
