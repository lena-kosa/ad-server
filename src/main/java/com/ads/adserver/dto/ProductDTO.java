package com.ads.adserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Product name should be provided")
    private String title;

    @NotBlank(message = "Product category should be provided")
    private String category;

    @NotBlank(message = "Product serial number should be provided")
    private String serialNumber;

    @PositiveOrZero(message = "Product price should be positive number")
    private BigDecimal price;
}
