package com.example.Inventory_Management.dto;

import lombok.Data;

@Data
public class OrderDto {
    long orderId; // Primary Key
    long productId;
    long quantity;
    String orderDate;
    String customerName;
    String status;
}
