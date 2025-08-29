package com.example.Inventory_Management.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long productId;

    String productName;

    String productBrand;

    long productPrice;
}
