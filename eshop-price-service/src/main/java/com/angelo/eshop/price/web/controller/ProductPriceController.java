package com.angelo.eshop.price.web.controller;

import com.angelo.eshop.price.model.ProductPrice;
import com.angelo.eshop.price.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-price")
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;

    @RequestMapping("/add")
    public String add(ProductPrice productPrice) {
        try {
            productPriceService.add(productPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update(ProductPrice productPrice) {
        try {
            productPriceService.update(productPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        try {
            productPriceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/findById")
    public ProductPrice findById(Long id) {
        try {
            return productPriceService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductPrice();
    }

}
