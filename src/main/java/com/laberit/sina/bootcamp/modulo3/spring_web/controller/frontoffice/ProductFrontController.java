package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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

    // <editor-fold> GET /products/{id}
    @GetMapping(value = "/{id}", produces = "application/json")
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

    @GetMapping("/{id}")
    public String showProductById(Model model, @PathVariable Long id) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        try {
            Product product = productService.getProductById(id);
            ProductDTO productDTO = new ProductDTO(product, lang);
            model.addAttribute("product", productDTO);
            return "product"; // Nombre de la vista Thymeleaf
        } catch (EntityNotFoundException e) {
            return "error/404";
        }
    }
    // </editor-fold>


    // <editor-fold> GET /products
    @GetMapping
    public String showProducts(Model model,
                               @RequestParam(value = "category", required = false) String category,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "20") int size) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        Page<ProductDTO> productPage = productService.getProductDTO(category, lang, name, PageRequest.of(page, size));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "products"; // Nombre de la vista Thymeleaf
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(value = "category", required = false) String category,
                                                        @RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "20") int size) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        Page<ProductDTO> productPage = productService.getProductDTO(category, lang, name, PageRequest.of(page, size));
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-TOTAL-COUNT", String.valueOf(productPage.getTotalElements()));
        return new ResponseEntity<>(productPage.getContent(), headers, HttpStatus.OK);
    }
    // </editor-fold>
}