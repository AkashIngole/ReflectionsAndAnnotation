package com.learning.spring;

public class Product {
    private String name;
    private Integer discount;
    private Double price;

    public Product(String name, Integer discount) {
        super();
        this.name = name;
        this.discount = discount;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public Integer getDiscount() { return this.discount; }

    public void setDiscount(Integer discount) { this.discount = discount; }

    public Double getPrice() { return this.price; }

    public void setPrice(Double v) {this.price = price; }

    @Override
    public String toString() {
        return "Product  [name= " + name +
                ", discount= " + discount +
                ", price= " + price +
                "]";
    }

}








