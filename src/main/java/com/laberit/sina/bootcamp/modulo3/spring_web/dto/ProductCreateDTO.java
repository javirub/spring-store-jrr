package com.laberit.sina.bootcamp.modulo3.spring_web.dto;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;


@Getter
public class ProductCreateDTO {
    @NotEmpty(message = "El código no puede estar vacío")
    private String code;

    @NotNull(message = "Debe ingresar un precio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;

    @NotNull(message = "Debe ingresar el stock")
    @PositiveOrZero(message = "El stock debe ser mayor o igual a 0")
    private Long stock;

    private ProductDetail[] productDetail;
}
