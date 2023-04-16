package com.learning.spring;

import com.learning.annotation.Component;

import java.util.List;

@Component
public class ProductRepository {

    public List<Product> getPrice(List<Product> items) {
        for(Product product : items) {

            //lets assume we are reading from DB
            Double price = (double) Math.round(30 * Math.random());

            System.out.println("Original Price of " + product.getName() + " is Rs." + price);
            product.setPrice(price);
        }
        return items;
    }
}
