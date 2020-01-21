package com.angelo.eshop.product.web.controller;

import com.angelo.eshop.product.model.ProductIntro;
import com.angelo.eshop.product.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-intro")
public class ProductIntroController {

    @Autowired
    private ProductIntroService productIntroService;

    @RequestMapping("/add")
    public String add(ProductIntro productIntro) {
        try {
            productIntroService.add(productIntro);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update(ProductIntro productIntro) {
        try {
            productIntroService.update(productIntro);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        try {
            productIntroService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/findById")
    public ProductIntro findById(Long id) {
        try {
            return productIntroService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductIntro();
    }

}
