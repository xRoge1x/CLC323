// ShoppingCartService.java
package com.OutdoorLife.service;

import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.model.ShoppingCartItem;
import com.OutdoorLife.model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private final ShoppingCart shoppingCart;

    public ShoppingCartService() {
        this.shoppingCart = new ShoppingCart();
    }

    public void addToCart(ProductModel product, int quantity) {
        ShoppingCartItem item = new ShoppingCartItem(product, quantity);
        shoppingCart.addItem(item);
    }

    public void removeFromCart(int index) {
        shoppingCart.removeItem(index);
    }

    public float getTotalPrice() {
        return shoppingCart.getTotalPrice();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void clearCart() {
        shoppingCart.clear();
    }
}
