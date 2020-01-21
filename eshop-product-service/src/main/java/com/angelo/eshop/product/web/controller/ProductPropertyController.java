package com.angelo.eshop.product.web.controller;

import com.angelo.eshop.product.model.ProductProperty;
import com.angelo.eshop.product.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-property")
public class ProductPropertyController {

    @Autowired
    private ProductPropertyService productPropertyService;

    @RequestMapping("/add")
    public String add(ProductProperty productProperty) {
        try {
            productPropertyService.add(productProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update(ProductProperty productProperty) {
        try {
            productPropertyService.update(productProperty);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        try {
            productPropertyService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/findById")
    public ProductProperty findById(Long id) {
        try {
            return productPropertyService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductProperty();
    }

}
