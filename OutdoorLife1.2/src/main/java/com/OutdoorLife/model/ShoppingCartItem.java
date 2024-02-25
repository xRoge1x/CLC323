// ShoppingCartItem.java
package com.OutdoorLife.model;

public class ShoppingCartItem {
    private ProductModel product;
    private int quantity;

    public ShoppingCartItem(ProductModel product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
