package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.CategoryMapper;
import com.angelo.eshop.product.model.Category;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(Category category, String operationType) {
        categoryMapper.add(category);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
    }

    @Override
    public void update(Category category, String operationType) {
        categoryMapper.update(category);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
    }

    @Override
    public void delete(Long id, String operationType) {
        categoryMapper.delete(id);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"category\", \"id\": " + id + "}");
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

}
