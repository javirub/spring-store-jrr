package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ProductFrontController {
    private static final Logger logger = LoggerFactory.getLogger(ProductFrontController.class);

    @GetMapping("/products")
    public String products(ModelMap model) {
        model.addAttribute("title", "Listado de productos");
        return "products";
    }

    @ModelAttribute("products")
    public List<Product> productsModel() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                "http://localhost:8080/backoffice/product",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        List<Product> products = response.getBody();
        logger.info("Productos obtenidos: {}", products);
        return products;
    }
}
