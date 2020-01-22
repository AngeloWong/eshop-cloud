package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductPropertyMapper;
import com.angelo.eshop.product.model.ProductProperty;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

    @Autowired
    private ProductPropertyMapper productPropertyMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(ProductProperty productProperty) {
        productPropertyMapper.add(productProperty);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product_property\", \"id\": " + productProperty.getId() + "}");
    }

    @Override
    public void update(ProductProperty productProperty) {
        productPropertyMapper.update(productProperty);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product_property\", \"id\": " + productProperty.getId() + "}");
    }

    @Override
    public void delete(Long id) {
        productPropertyMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product_property\", \"id\": " + id + "}");
    }

    @Override
    public ProductProperty findById(Long id) {
        return productPropertyMapper.findById(id);
    }

}
