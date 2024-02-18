package com.OutdoorLife.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductModel {

	private Long productId;

    private String description;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Min(value = 0, message = "Price must be a positive number")
    private int price;

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
