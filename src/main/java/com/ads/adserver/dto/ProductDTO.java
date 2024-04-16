package com.ads.adserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private String category;
    private String serialNumber;
    private BigDecimal price;
}
