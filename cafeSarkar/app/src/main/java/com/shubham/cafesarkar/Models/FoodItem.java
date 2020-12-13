package com.shubham.cafesarkar.Models;

public class FoodItem {
    String name;
    String quantity;
    String category;
    Integer costPrice;
    Integer sellingPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", category='" + category + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
