package com.angelo.eshop.datasync.rabbitmq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.angelo.eshop.datasync.service.EshopProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * 数据同步服务，就是获取各种院子数据的变更消息
 * （1）然后通过 spring cloud fegion调用 eshop-product-service服务的各种接口，获取数据
 * （2）将原子数据在redis中进行增删改
 * （3）将维度数据变化消息写入rabbitmq中另外一个queue，供数据聚合服务来消费
 */
@Slf4j
@Component
@RabbitListener(queues = "data-change-queue")
public class DataChangeQueueReceiver {

    @Autowired
    private EshopProductService eshopProductService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    private Set<String> dimDataChangeMessageSet = Collections.synchronizedSet(new HashSet<String>());

    private List<JSONObject> brandDataChangeMessageList = new ArrayList<>();

    public DataChangeQueueReceiver() {
        new SendThread().start();
    }

    @RabbitHandler
    public void process(String message) {
        log.info("[data-change-queue] receive the message:"  + message);
        // 对这个message进行解析
        JSONObject jsonObject = JSONObject.parseObject(message);
        // 先获取data_type
        String dataType = jsonObject.getString("data_type");
        if ("brand".equals(dataType)) {
            processBrandDataChangeMessage(jsonObject);
        } else if ("category".equals(dataType)) {
            processCategoryDataChangeMessage(jsonObject);
        } else if ("product_intro".equals(dataType)) {
            processProductIntroDataChangeMessage(jsonObject);
        } else if ("product_property".equals(dataType)) {
            processProductPropertyDataChangeMessage(jsonObject);
        } else if ("product".equals(dataType)) {
            processProductDataChangeMessage(jsonObject);
        } else if ("product_specification".equals(dataType)) {
            processProductSpecificationDataChangeMessage(jsonObject);
        }
    }

    private void processBrandDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            brandDataChangeMessageList.add(messageJSONObject);

            log.info("【将品牌数据放入内存list中】, list.size() = " + brandDataChangeMessageList.size());

            if (brandDataChangeMessageList.size() >= 2) {
                log.info("【品牌数据内存list大小大于等于2，开始执行批量调用】");

                String ids = "";

                for (int i = 0; i < brandDataChangeMessageList.size(); i++) {
                    ids += brandDataChangeMessageList.get(i).getLong("id");
                    if (i < brandDataChangeMessageList.size() - 1) {
                        ids += ",";
                    }
                }

                log.info("【品牌数据ids生成】 ids = " + ids);

                JSONArray brandJSONArray = JSONArray.parseArray(eshopProductService.findBrandByIds(ids));

                log.info("【通过批量调用获取到品牌数据】 brandJSONArray = " + brandJSONArray.toJSONString());

                for (int i = 0; i < brandJSONArray.size(); i++) {
                    JSONObject dataJSONObject = brandJSONArray.getJSONObject(i);
                    Jedis jedis = jedisPool.getResource();
                    jedis.set("brand_" + dataJSONObject.getLong("id"), dataJSONObject.toJSONString());

                    log.info("【将品牌数据写入redis】 brandId = " + dataJSONObject.getLong("id") + " , dataJSONObject =>" + dataJSONObject.toJSONString() );

                    dimDataChangeMessageSet.add("{\"dim_type\": \"brand\", \"id\": " + dataJSONObject.getLong("id") + "}");

                    log.info("【将品牌数据写入内存去重set中】 brandId = " + dataJSONObject.getLong("id"));
                }
                brandDataChangeMessageList.clear();
            }
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("brand_" + id);
            dimDataChangeMessageSet.add("{\"dim_type\": \"brand\", \"id\": " + id + "}");
            log.info("【品牌维度数据变更消息被放入内存Set中】");
        }
    }

    private void processCategoryDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findCategoryById(id));
            Jedis jedis = jedisPool.getResource();
            jedis.set("category_" + dataJSONObject.getLong("id"), dataJSONObject.toJSONString());
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("category_" + id);
        }

        dimDataChangeMessageSet.add("{\"dim_type\": \"category\", \"id\": " + id + "}");
    }

    private void processProductIntroDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        Long productId = messageJSONObject.getLong("product_id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductIntroById(id));
            Jedis jedis = jedisPool.getResource();
            jedis.set("product_intro_" + productId, dataJSONObject.toJSONString());
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("product_intro_" + productId);
        }

        dimDataChangeMessageSet.add("{\"dim_type\": \"product_intro\", \"id\": " + productId + "}");
    }

    private void processProductDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductById(id));
            Jedis jedis = jedisPool.getResource();
            jedis.set("product_" + id, dataJSONObject.toJSONString());
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("product_" + id);
        }

        dimDataChangeMessageSet.add("{\"dim_type\": \"product\", \"id\": " + id + "}");
    }

    private void processProductPropertyDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        Long productId = messageJSONObject.getLong("product_id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductPropertyById(id));
            Jedis jedis = jedisPool.getResource();
            jedis.set("product_property_" + productId, dataJSONObject.toJSONString());
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("product_property_" + productId);
        }

        dimDataChangeMessageSet.add("{\"dim_type\": \"product\", \"id\": " + productId + "}");
    }

    private void processProductSpecificationDataChangeMessage(JSONObject messageJSONObject) {
        Long id = messageJSONObject.getLong("id");
        Long productId = messageJSONObject.getLong("product_id");
        String eventType = messageJSONObject.getString("event_type");

        if ("add".equals(eventType) || "update".equals(eventType)) {
            JSONObject dataJSONObject = JSONObject.parseObject(eshopProductService.findProductSpecificationById(id));
            Jedis jedis = jedisPool.getResource();
            jedis.set("product_specification_" + productId, dataJSONObject.toJSONString());
        } else if ("delete".equals(eventType)) {
            Jedis jedis = jedisPool.getResource();
            jedis.del("product_specification_" + productId);
        }

        dimDataChangeMessageSet.add("{\"dim_type\": \"product\", \"id\": " + productId + "}");
    }
    private class SendThread extends Thread {

        @Override
        public void run() {
            while (true) {
                if (!dimDataChangeMessageSet.isEmpty()) {
                    for (String message : dimDataChangeMessageSet) {
                        rabbitMQSender.send("aggr-data-change-queue", message);
                        log.info("【将去重后的维度数据变更消息发送到下一个queue[aggr-data-change-queue]】, messge =>" + message);
                    }
                    dimDataChangeMessageSet.clear();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
