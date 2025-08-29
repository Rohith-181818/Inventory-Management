package com.example.Inventory_Management.controller;

import com.example.Inventory_Management.model.Product;
import com.example.Inventory_Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("update/product/{productId}")
    public Product updateProduct(@PathVariable("productId") long productId, @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }

    @GetMapping("/get/productById/{productId}")
    public Product getProductById(@PathVariable("productId") long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/get/allProducts")
    public ArrayList<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/product/{productId}")
    public void deleteProductById(@PathVariable("productId") long productId){
        productService.deleteProductById(productId);
    }
}
