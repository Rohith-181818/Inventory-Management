package com.example.Inventory_Management.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryId;

    private long productId;

    private long availableQuantity;



}
