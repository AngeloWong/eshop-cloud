package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductSpecificationMapper;
import com.angelo.eshop.product.model.ProductSpecification;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(ProductSpecification productSpecification) {
        productSpecificationMapper.add(productSpecification);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");
    }

    @Override
    public void update(ProductSpecification productSpecification) {
        productSpecificationMapper.update(productSpecification);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");
    }

    @Override
    public void delete(Long id) {
        ProductSpecification productSpecification = findById(id);
        productSpecificationMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");

    }

    @Override
    public ProductSpecification findById(Long id) {
        return productSpecificationMapper.findById(id);
    }

}
