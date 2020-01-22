package com.angelo.eshop.product.service.impl;

import com.angelo.eshop.product.mapper.BrandMapper;
import com.angelo.eshop.product.model.Brand;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(Brand brand) {
        brandMapper.add(brand);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"brand\", \"id\": " + brand.getId() + "}");
    }

    @Override
    public void delete(Long id) {
        brandMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"brand\", \"id\": " + id + "}");
    }

    @Override
    public Brand findById(Long id) {
        return brandMapper.findById(id);
    }

}
