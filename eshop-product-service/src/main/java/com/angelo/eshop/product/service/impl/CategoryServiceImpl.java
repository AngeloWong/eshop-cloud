package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.CategoryMapper;
import com.angelo.eshop.product.model.Category;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
    }

    @Override
    public void delete(Long id) {
        categoryMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"category\", \"id\": " + id + "}");
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

}
