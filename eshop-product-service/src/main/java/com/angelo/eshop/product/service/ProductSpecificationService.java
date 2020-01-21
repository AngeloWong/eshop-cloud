package com.angelo.eshop.product.service;


import com.angelo.eshop.product.model.ProductSpecification;

public interface ProductSpecificationService {

    public void add(ProductSpecification productSpecification);

    public void update(ProductSpecification productSpecification);

    public void delete(Long id);

    public ProductSpecification findById(Long id);

}
