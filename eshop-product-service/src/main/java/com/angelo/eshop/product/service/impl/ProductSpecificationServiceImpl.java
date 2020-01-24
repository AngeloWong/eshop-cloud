package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.ProductSpecificationMapper;
import com.angelo.eshop.product.model.ProductSpecification;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(ProductSpecification productSpecification, String operationType) {
        productSpecificationMapper.add(productSpecification);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");
    }

    @Override
    public void update(ProductSpecification productSpecification, String operationType) {
        productSpecificationMapper.update(productSpecification);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");
    }

    @Override
    public void delete(Long id, String operationType) {
        ProductSpecification productSpecification = findById(id);
        productSpecificationMapper.delete(id);

        String queue = null;
        if (StringUtils.isEmpty(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_specification\", " +
                "\"id\": " + productSpecification.getId() + ", \"product_id\":" + productSpecification.getProductId()+"}");

    }

    @Override
    public ProductSpecification findById(Long id) {
        return productSpecificationMapper.findById(id);
    }

}
