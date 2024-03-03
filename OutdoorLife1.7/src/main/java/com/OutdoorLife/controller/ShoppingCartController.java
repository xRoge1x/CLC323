// ShoppingCartController.java
package com.OutdoorLife.controller;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.service.ProductService;
import com.OutdoorLife.service.ShoppingCartService;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final ProductService productService;
    
    @Autowired
    private LoggerInterceptor logger;

    public ShoppingCartController(ShoppingCartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView viewCart() {
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "viewCart", "Viewing shopping cart");
        
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cart", cartService.getShoppingCart());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAddToCartForm() {
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "showAddToCartForm", "Showing add to cart form");
        
        ModelAndView modelAndView = new ModelAndView("addToCart");
        modelAndView.addObject("products", productService.getAllProducts());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        ProductModel product = productService.getProductById(productId);
        cartService.addToCart(product, quantity);
        
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "addToCart", "Added product to cart: " + product.getProductName() + ", Quantity: " + quantity);
        
        return "redirect:/cart";
    }

    @PostMapping("/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        cartService.removeFromCart(index);
        
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "removeFromCart", "Removed item from cart at index: " + index);
        
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout() {
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "checkout", "Checkout initiated");
        
        // Redirect to the summary page
        return "redirect:/cart/summary";
    }

    @GetMapping("/summary")
    public ModelAndView showSummary() {
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "showSummary", "Showing cart summary");
        
        ModelAndView modelAndView = new ModelAndView("summary");
        modelAndView.addObject("cart", cartService.getShoppingCart());
        modelAndView.addObject("totalPrice", cartService.getTotalPrice());
        return modelAndView;
    }

    @PostMapping("/confirm")
    public String confirmCheckout() {
        logger.log(Level.INFO, ShoppingCartController.class.getName(), "confirmCheckout", "Checkout confirmed. Cart cleared.");
        
        // Clear the shopping cart upon checkout
        cartService.clearCart();
        return "redirect:/cart";
    }
}
