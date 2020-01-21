package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.CategoryMapper;
import com.angelo.eshop.product.model.Category;
import com.angelo.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.delete(id);
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

}
