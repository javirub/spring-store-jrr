package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductFrontRestController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    // Obtener un producto por id [READ]
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productServiceImpl.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("No se ha encontrado el producto con id: "+ id,HttpStatus.NOT_FOUND);
        }
    }
}
