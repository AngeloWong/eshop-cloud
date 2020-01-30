package com.angelo.eshop.price.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.angelo.eshop.price.mapper.ProductPriceMapper;
import com.angelo.eshop.price.model.ProductPrice;
import com.angelo.eshop.price.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceMapper productPriceMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(ProductPrice productPrice) {
        productPriceMapper.add(productPrice);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
    }

    @Override
    public void update(ProductPrice productPrice) {
        productPriceMapper.update(productPrice);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
    }

    @Override
    public void delete(Long id) {
        ProductPrice productPrice = findById(id);
        productPriceMapper.delete(id);
        Jedis jedis = jedisPool.getResource();
        jedis.del("product_price_" + productPrice.getProductId());
    }

    @Override
    public ProductPrice findById(Long id) {
        return productPriceMapper.findById(id);
    }

    @Override
    public ProductPrice findByProductId(Long productId) {
        Jedis jedis = jedisPool.getResource();
        String dataJSON = jedis.get("product_price_" + productId);
        if (!StringUtils.isEmpty(dataJSON)) {
            JSONObject dataJSONObject = JSONObject.parseObject(dataJSON);
            dataJSONObject.put("id", "-1");
            return dataJSONObject.toJavaObject(ProductPrice.class);
        } else {
            return productPriceMapper.findByProductId(productId);
        }
    }
}
