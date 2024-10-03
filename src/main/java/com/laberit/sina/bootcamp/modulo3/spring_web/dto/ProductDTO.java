package com.laberit.sina.bootcamp.modulo3.spring_web.dto;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import lombok.Getter;

@Getter
public class ProductDTO {
    private final Long id;
    private final String code;
    private final Double price;
    private String name;
    private String description;

    public ProductDTO(Product product, String lang) {
        this.id = product.getId();
        this.code = product.getCode();
        this.price = product.getPrice();
        switch(lang){
            case "es":
                product.getProductDetail().stream().filter(pd -> pd.getLang().equals("es")).findFirst().ifPresent(pd -> {
                    this.name = pd.getName();
                    this.description = pd.getDescription();
                });
                break;
            case "en":
                product.getProductDetail().stream().filter(pd -> pd.getLang().equals("en")).findFirst().ifPresent(pd -> {
                    this.name = pd.getName();
                    this.description = pd.getDescription();
                });
                break;
            default: // default to spanish
                product.getProductDetail().stream().filter(pd -> pd.getLang().equals("es")).findFirst().ifPresent(pd -> {
                    this.name = pd.getName();
                    this.description = pd.getDescription();
                });
                break;
        }
    }
}
