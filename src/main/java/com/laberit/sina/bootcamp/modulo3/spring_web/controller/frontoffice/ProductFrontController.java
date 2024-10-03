package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductFrontController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public String showProducts(Model model) {
        // Aquí deberías obtener los productos de tu API o base de datos
        List<Product> products = productServiceImpl.getAllProducts();
        // Añadir los productos al modelo
        model.addAttribute("products", products);
        return "products"; // Nombre de la vista Thymeleaf
    }
}
