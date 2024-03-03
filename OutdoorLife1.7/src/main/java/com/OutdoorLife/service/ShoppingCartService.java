// ShoppingCartService.java
package com.OutdoorLife.service;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.model.ShoppingCartItem;
import com.OutdoorLife.model.ShoppingCart;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private final ShoppingCart shoppingCart;
    
    @Autowired
    private LoggerInterceptor logger;

    public ShoppingCartService() {
        this.shoppingCart = new ShoppingCart();
    }

    public void addToCart(ProductModel product, int quantity) {
        ShoppingCartItem item = new ShoppingCartItem(product, quantity);
        shoppingCart.addItem(item);

        logger.log(Level.INFO, ShoppingCartService.class.getName(), "addToCart", "Added product to cart: " + product.getProductName());
    }

    public void removeFromCart(int index) {
        shoppingCart.removeItem(index);

        logger.log(Level.INFO, ShoppingCartService.class.getName(), "removeFromCart", "Removed item from cart at index: " + index);
    }

    public float getTotalPrice() {
        float totalPrice = shoppingCart.getTotalPrice();

        logger.log(Level.INFO, ShoppingCartService.class.getName(), "getTotalPrice", "Total price retrieved: " + totalPrice);

        return totalPrice;
    }

    public ShoppingCart getShoppingCart() {
        ShoppingCart cart = shoppingCart;

        logger.log(Level.INFO, ShoppingCartService.class.getName(), "getShoppingCart", "Shopping cart retrieved");

        return cart;
    }

    public void clearCart() {
        shoppingCart.clear();

        logger.log(Level.INFO, ShoppingCartService.class.getName(), "clearCart", "Shopping cart cleared");
    }
}
