package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductFrontRestController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    // Obtener un producto por id [READ]
    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productServiceImpl.getProductById(id);
    }
}
