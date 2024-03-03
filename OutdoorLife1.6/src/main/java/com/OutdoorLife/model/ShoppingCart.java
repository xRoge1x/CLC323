// ShoppingCart.java
package com.OutdoorLife.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public float getTotalPrice() {
        float total = 0;
        for (ShoppingCartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
