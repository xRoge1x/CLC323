package com.OutdoorLife.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/doCreate")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "product";
    }

    @PostMapping("/doCreate")
    public String createProduct(@ModelAttribute("product") @Valid ProductModel product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "product";
        }

        // Convert ProductModel to ProductEntity and save to the database
        productService.createProduct(product);

        // Redirect to a success page
        return "productSuccess" + (product.getProductId() != null ? product.getProductId() : "");
    }

    @GetMapping("/productSuccess/{productId}")
    public String showSuccessPage(@ModelAttribute("productId") Long productId, Model model) {
        // Retrieve the product from the database
        ProductModel product = productService.getProductById(productId);
        model.addAttribute("product", product);
        
        return "productSuccess";
    }
    
    // Show created products
    @GetMapping("/createdProducts")
    public String showCreatedProducts(Model model) {
    	
        // Retrieve the products from the database
        model.addAttribute("products", productService.getAllProducts());
        
        return "createdProducts";
    }
    
    // Update product form
    @GetMapping("/{id}/updateProduct")
    public String updateProductForm(@PathVariable Long id, Model model) {
        ProductModel productModel = productService.getProductById(id);
        model.addAttribute("product", productModel);
        return "updateProduct";
    }
    
    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") @Valid ProductModel updatedProduct, BindingResult bindingResult) 
    {
    	if (bindingResult.hasErrors()) {
            
            return "updateProduct";
        }

        updatedProduct.setProductId(id);
        productService.updateProduct(updatedProduct);
        
        return "redirect:/products/createdProducts";
    }
    
    // Delete product form
    @GetMapping("/{id}/deleteProduct")
    public String deleteProduct(@PathVariable("id") Long id) {
    	
        productService.deleteProduct(id);
        
        return "redirect:/products/createdProducts";
    }
    
    @PostMapping("/productSuccess")
    public String showSuccessPagePost() {
        return "doCreate";
    }
}
