package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductServiceImpl;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductFrontController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public String showProducts(Model model, @RequestParam(value = "lang", defaultValue = "es") String lang, @RequestParam(value="category", required = false) String category) {
        List<Product> products;
        try {
            if (category != null && EnumUtils.isValidCategory(category)) {
                Category categoryEnum = Category.valueOf(category.toUpperCase());
                products = productServiceImpl.getByCategory(categoryEnum);
            } else {
                products = productServiceImpl.getAllProducts();
            }
        } catch (IllegalArgumentException e) {
            products = productServiceImpl.getAllProducts();
        }

        List<ProductDTO> productDTOs = products.stream()
                .map(product -> new ProductDTO(product, lang))
                .toList();
        // Añadir los productos al modelo
        model.addAttribute("products", productDTOs);
        // Añadir categorías de productos
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return "products"; // Nombre de la vista Thymeleaf
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<ProductDTO> showProductsJson(@RequestParam(value = "lang", defaultValue = "es") String lang) {
        List<Product> products = productServiceImpl.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> new ProductDTO(product, lang))
                .toList();
        return productDTOs;
    }
}
