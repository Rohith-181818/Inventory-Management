package com.example.Inventory_Management.controller;

import com.example.Inventory_Management.dto.InventoryDto;
import com.example.Inventory_Management.model.Inventory;
import com.example.Inventory_Management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add/inventory")
    public Inventory addItem(@RequestBody InventoryDto inventory){
        return inventoryService.addItem(inventory);
    }

    @PutMapping("/update/inventory")
    public Optional<Inventory> updateItem(@RequestParam long productId, @RequestParam int newQuantity){
        return inventoryService.updateItem(productId,newQuantity);
    }

    @GetMapping("/get/inventoryById/{inventoryId}")
    public Inventory getInventoryById(@PathVariable("inventoryId") long inventoryId){
        return inventoryService.getInventoryById(inventoryId);
    }


    @GetMapping("/get/allInventories")
    public ArrayList<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @DeleteMapping("/delete/inventory/{inventoryId}")
    public void deleteInventoryById(@PathVariable("inventoryId") long inventoryId){
        inventoryService.deleteInventoryById(inventoryId);
    }


}
