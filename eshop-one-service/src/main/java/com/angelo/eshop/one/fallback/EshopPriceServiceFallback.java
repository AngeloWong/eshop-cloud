package com.angelo.eshop.one.fallback;

import com.alibaba.fastjson.JSONObject;
import com.angelo.eshop.one.service.EshopPriceService;
import org.springframework.stereotype.Component;

@Component
public class EshopPriceServiceFallback implements EshopPriceService {
    @Override
    public String findByProductId(Long productId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "-1");
        jsonObject.put("productId", productId);
        jsonObject.put("value", "-1");
        return jsonObject.toJSONString();
    }
}
