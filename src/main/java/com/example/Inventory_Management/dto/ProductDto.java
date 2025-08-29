package com.example.Inventory_Management.dto;

import lombok.Data;

@Data
public class ProductDto {
    long productId; // Primary Key
    String productName;
    String productBrand;
    long productPrice;

}
