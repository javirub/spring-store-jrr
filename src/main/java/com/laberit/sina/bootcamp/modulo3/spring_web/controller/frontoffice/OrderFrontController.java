package com.laberit.sina.bootcamp.modulo3.spring_web.controller.frontoffice;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.User;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.UserRepository;
import com.laberit.sina.bootcamp.modulo3.spring_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class OrderFrontController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/order")
    public String createOrder(Model model,
                              @RequestParam Long productId,
                              @RequestParam Integer quantity,
                              Principal principal) {
        try {
            User user = userRepository.findByEmailOrThrow(principal.getName());
            Product product = productRepository.findById(productId).orElseThrow();
            orderService.createOrder(product, user, quantity);
            String lang = LocaleContextHolder.getLocale().getLanguage();
            ProductDTO productDTO = new ProductDTO(product, lang);
            model.addAttribute("productName", productDTO.getName());
            model.addAttribute("quantity", quantity);
            return "order/success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "order/error";
        }
    }
}
