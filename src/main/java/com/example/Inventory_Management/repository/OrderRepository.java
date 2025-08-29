package com.example.Inventory_Management.repository;

import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByProductId(long productId);
}
