package com.example.Inventory_Management.controller;

import com.example.Inventory_Management.dto.InventoryDto;
import com.example.Inventory_Management.dto.OrderDto;
import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.model.Order;
import com.example.Inventory_Management.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/order")
    public Order createOrder(@RequestBody OrderDto order){
        return orderService.createOrder(order);
    }


    @GetMapping("/get/orderById/{orderId}")
    public Order getOrderById(@PathVariable("orderId") long orderId){
        return orderService.getOrderById(orderId);
    }


    @GetMapping("/get/allOrders")
    public ArrayList<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/delete/order/{orderId}")
    public void deleteOrderById(@PathVariable("orderId") long orderId){
        orderService.deleteOrderById(orderId);
    }
}
