package com.OutdoorLife.service;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.entity.ProductEntity;
import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.event.Level;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private LoggerInterceptor logger;
    
    @Transactional
    public void createProduct(ProductModel productModel) {
        // Convert ProductModel to ProductEntity
        ProductEntity productEntity = convertToEntity(productModel);

        // Save the product to the database
        productRepository.save(productEntity);

        logger.log(Level.INFO, ProductService.class.getName(), "createProduct", "Product created: " + productEntity);
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        
        logger.log(Level.INFO, ProductService.class.getName(), "deleteProduct", "Product deleted with ID: " + id);
    }
    
    // Update the selected product and save it to the database
    @Transactional
    public void updateProduct(ProductModel updatedProduct) {
        // Retrieve the existing product from the database
        ProductEntity existingProduct = productRepository.findById(updatedProduct.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Update the existing product with the new data
        BeanUtils.copyProperties(updatedProduct, existingProduct, "productId");

        // Save the updated product to the database
        productRepository.save(existingProduct);
        
        logger.log(Level.INFO, ProductService.class.getName(), "updateProduct", "Product updated: " + existingProduct);
    }
    
    public ProductModel getProductById(Long productId) {
        // Retrieve the product from the database
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Convert ProductEntity to ProductModel
        return convertToModel(productEntity);
    }

    private ProductEntity convertToEntity(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        
        // Copy fields from ProductModel to ProductEntity
        BeanUtils.copyProperties(productModel, productEntity);
        
        return productEntity;
    }

    private ProductModel convertToModel(ProductEntity productEntity) {
        ProductModel productModel = new ProductModel();
        
        // Copy fields from ProductEntity to ProductModel
        BeanUtils.copyProperties(productEntity, productModel);
        
        return productModel;
    }
    
    // Find the product by name
    public ProductModel getProductByName(String productName) {
        ProductEntity productEntity = productRepository.findByProductName(productName);
        return convertToModel(productEntity);
    }

    // Return all products method
    public List<ProductModel> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        
        logger.log(Level.INFO, ProductService.class.getName(), "getAllProducts", "All products returned");
        
        return productEntities.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }
    
}
