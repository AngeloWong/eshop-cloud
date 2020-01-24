package com.angelo.eshop.product.service;


import com.angelo.eshop.product.model.Product;

public interface ProductService {

    public void add(Product product, String operationType);

    public void update(Product product, String operationType);

    public void delete(Long id, String operationType);

    public Product findById(Long id);

}
