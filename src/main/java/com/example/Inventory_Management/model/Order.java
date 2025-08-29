package com.example.Inventory_Management.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private long productId;


    private long quantity;

    private String orderDate;

    private String customerName;

    private String status;
}
