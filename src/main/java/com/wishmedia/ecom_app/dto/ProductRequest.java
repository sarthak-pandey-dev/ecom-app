package com.wishmedia.ecom_app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String discription;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private String imageUrl;

}
