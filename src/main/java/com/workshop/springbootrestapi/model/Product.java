package com.workshop.springbootrestapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Product {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    public Product() {}

    public Product(String name, BigDecimal price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}