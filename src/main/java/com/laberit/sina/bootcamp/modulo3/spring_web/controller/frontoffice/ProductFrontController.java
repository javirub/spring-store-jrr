package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductService;
import com.laberit.sina.bootcamp.modulo3.spring_web.utils.EnumUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductFrontController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        try {
            Product product = productService.getProductById(id);
            ProductDTO productDTO = new ProductDTO(product, lang); //
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("No se ha encontrado el producto con id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public String showProducts(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "name", required = false) String name) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        // Añadir los productos al modelo
        model.addAttribute("products", getProductDTO(category, lang, name).stream()
                .filter(product -> name == null || product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList());
        // Añadir categorías de productos
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return "products"; // Nombre de la vista Thymeleaf
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<ProductDTO> showProductsJson(@RequestParam(value = "category", required = false) String category,
                                             @RequestParam(value = "name", required = false) String name) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        List<ProductDTO> products = getProductDTO(category, lang, name);
        if (name != null && !name.isEmpty()) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        return products;
    }


    private List<ProductDTO> getProductDTO(String category, String lang, String name) {
        List<Product> products;
        if (category != null && EnumUtils.isValidCategory(category)) {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            if (name != null && !name.isEmpty()) {
                products = productService.getAllProductsByCategoryWithDetailFilterByName(categoryEnum, name, lang);
            } else {
                products = productService.getAllProductsByCategoryWithDetail(categoryEnum);
            }
        } else {
            if (name != null && !name.isEmpty()) {
                products = productService.getAllProductsWithDetailFilterByName(name, lang);
            } else {
                products = productService.getAllProductsWithDetail();
            }
        }

        return products.stream()
                .map(product -> new ProductDTO(product, lang))
                .toList();
    }
}