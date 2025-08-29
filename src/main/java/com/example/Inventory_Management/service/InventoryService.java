package com.example.Inventory_Management.service;

import com.example.Inventory_Management.dto.InventoryDto;
import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.model.Product;
import com.example.Inventory_Management.repository.InventoryRepository;
import com.example.Inventory_Management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Inventory addItem(InventoryDto inventory){
        try{
            Optional<Product> addingProduct = productRepository.findById((int) inventory.getProductId());
            if(addingProduct.isPresent()){
                Inventory addingInventory = new Inventory();
                addingInventory.setProductId(inventory.getProductId());
                addingInventory.setAvailableQuantity(inventory.getAvailableQuantity());
                return inventoryRepository.save(addingInventory);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @CachePut("inventories")
    public Optional<Inventory> updateItem(long productId, int newQuantity){
        Optional<Inventory> newItem = inventoryRepository.findByProductId(productId);
        if (newItem.isPresent()) {
            Inventory inventory = newItem.get();
            inventory.setAvailableQuantity(newQuantity);
            return Optional.of(inventoryRepository.save(inventory));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with product ID " + productId + " not found");
        }
    }

    @Cacheable("inventories")
    public Inventory getInventoryById(long inventoryId){
        try{
            return inventoryRepository.findById((int) inventoryId).get();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable("inventories")
    public ArrayList<Inventory> getAllInventories(){
        List<Inventory> allInventories = inventoryRepository.findAll();
        return new ArrayList<>(allInventories);
    }

    @CacheEvict("inventories")
    public void deleteInventoryById(long inventoryId){
        inventoryRepository.deleteById((int) inventoryId);
    }
}
