package com.angelo.eshop.price.service;

import com.angelo.eshop.price.model.ProductPrice;

public interface ProductPriceService {

    public void add(ProductPrice productPrice);

    public void update(ProductPrice productPrice);

    public void delete(Long id);

    public ProductPrice findById(Long id);

}
