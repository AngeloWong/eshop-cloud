package com.angelo.eshop.product.service;


import com.angelo.eshop.product.model.ProductProperty;

public interface ProductPropertyService {

    public void add(ProductProperty productProperty);

    public void update(ProductProperty productProperty);

    public void delete(Long id);

    public ProductProperty findById(Long id);

}