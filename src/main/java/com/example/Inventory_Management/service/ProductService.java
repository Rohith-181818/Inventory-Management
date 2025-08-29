package com.example.Inventory_Management.service;

import com.example.Inventory_Management.model.Product;
import com.example.Inventory_Management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @CachePut("products")
    public Product updateProduct(long productId, Product product){
        try{
            Product newProduct = productRepository.findById((int) productId).get();
            if(product.getProductName() != null){
                newProduct.setProductName(product.getProductName());
            }
            if(product.getProductBrand() != null){
                newProduct.setProductBrand(product.getProductBrand());
            }
            if(product.getProductPrice() != 0){
                newProduct.setProductPrice(product.getProductPrice());
            }

            productRepository.save(newProduct);
            return newProduct;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable("products")
    public Product getProductById(long productId){
        try{
            return productRepository.findById((int) productId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable("products")
    public ArrayList<Product> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return new ArrayList<>(allProducts);
    }

    @CacheEvict("products")
    public void deleteProductById(long productId){
        productRepository.deleteById((int) productId);
    }

}
