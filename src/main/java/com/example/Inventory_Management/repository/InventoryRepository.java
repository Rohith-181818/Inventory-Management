package com.example.Inventory_Management.repository;

import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByProductId(long productId);
}
