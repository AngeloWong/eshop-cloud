package com.angelo.eshop.product.service;


import com.angelo.eshop.product.model.Category;

public interface CategoryService {

    public void add(Category category);

    public void update(Category category);

    public void delete(Long id);

    public Category findById(Long id);

}
