package com.angelo.eshop.one.fallback;

import com.alibaba.fastjson.JSONObject;
import com.angelo.eshop.one.service.EshopInventoryService;
import org.springframework.stereotype.Component;

@Component
public class EshopInventoryServiceFallback implements EshopInventoryService {

    @Override
    public String findByProductId(Long productId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "-1");
        jsonObject.put("productId", productId);
        jsonObject.put("value", "0");
        return jsonObject.toJSONString();
    }
}
