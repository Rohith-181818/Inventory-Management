package com.example.Inventory_Management.dto;

import lombok.Data;

@Data
public class InventoryDto {
    long inventoryId; // Primary Key
    long productId; // Foreign Key
    long availableQuantity;

}
