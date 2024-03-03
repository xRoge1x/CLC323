package com.OutdoorLife.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("orders")
public class OrderEntity {

    @Id
    private Long id;

    @Column("ORDER_NO")
    private String orderNo;
    
    @Column("PRODUCT_NAME")
    private String productName;
    
    @Column("PRICE")
    private float price;
    
    @Column("QUANTITY")
    private int quantity;
    


    public OrderEntity() {
    	
    }

    public OrderEntity(String orderNo, String productName, float price, int quantity) {
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
