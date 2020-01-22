package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductMapper;
import com.angelo.eshop.product.model.Product;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(Product product) {
        productMapper.add(product);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");
    }

    @Override
    public void delete(Long id) {
        productMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product\", \"id\": " + id + "}");
    }

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

}
