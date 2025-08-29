package com.example.Inventory_Management.service;

import com.example.Inventory_Management.dto.OrderDto;
import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.model.Order;
import com.example.Inventory_Management.model.Product;
import com.example.Inventory_Management.repository.InventoryRepository;
import com.example.Inventory_Management.repository.OrderRepository;
import com.example.Inventory_Management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(OrderDto order){
        try{
            Optional<Product> addingProduct = productRepository.findById((int) order.getProductId());
            if(addingProduct.isPresent()){
                Optional<Inventory> inventory = (inventoryRepository.findByProductId(addingProduct.get().getProductId()));
                if(inventory.isPresent() && inventory.get().getAvailableQuantity()>= order.getQuantity()){
                    Order creatingOrder = new Order();
                    creatingOrder.setProductId(order.getProductId());
                    creatingOrder.setOrderDate(order.getOrderDate());
                    creatingOrder.setQuantity(order.getQuantity());
                    creatingOrder.setCustomerName(order.getCustomerName());
                    creatingOrder.setStatus("Success");

                    Inventory in = inventory.get();
                    in.setAvailableQuantity(in.getAvailableQuantity() - order.getQuantity());
                    inventoryRepository.save(in);
                    return orderRepository.save(creatingOrder);
                }
                else{
                    Order creatingOrder = new Order();
                    creatingOrder.setProductId(order.getProductId());
                    creatingOrder.setOrderDate(order.getOrderDate());
                    creatingOrder.setQuantity(order.getQuantity());
                    creatingOrder.setCustomerName(order.getCustomerName());
                    creatingOrder.setStatus("Fail");

                    System.out.println("Available Quantity : " + order.getQuantity());
                    return orderRepository.save(creatingOrder);
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Cacheable("orders")
    public Order getOrderById(@PathVariable("orderId") long orderId){
        try{
            return orderRepository.findById((int) orderId).get();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable("orders")
    public ArrayList<Order> getAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        return new ArrayList<>(allOrders);
    }

    @CacheEvict("orders")
    public void deleteOrderById(@PathVariable("orderId") long orderId){
        orderRepository.deleteById((int) orderId);
    }

}









