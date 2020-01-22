package com.angelo.eshop.product.service.impl;


import com.angelo.eshop.product.mapper.ProductIntroMapper;
import com.angelo.eshop.product.model.ProductIntro;
import com.angelo.eshop.product.rabbitmq.RabbitMQSender;
import com.angelo.eshop.product.rabbitmq.RabbitQueue;
import com.angelo.eshop.product.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

    @Autowired
    private ProductIntroMapper productIntroMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void add(ProductIntro productIntro) {
        productIntroMapper.add(productIntro);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + "}");
    }

    @Override
    public void update(ProductIntro productIntro) {
        productIntroMapper.update(productIntro);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"product_intro\", \"id\": " + productIntro.getId() + "}");
    }

    @Override
    public void delete(Long id) {
        productIntroMapper.delete(id);
        rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"product_intro\", \"id\": " + id + "}");
    }

    @Override
    public ProductIntro findById(Long id) {
        return productIntroMapper.findById(id);
    }

}
