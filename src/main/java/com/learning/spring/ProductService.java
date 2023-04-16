package com.learning.spring;

import com.learning.annotation.Autowired;
import com.learning.annotation.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getFinalPriceList(List<Product> items) {
        List<Product> list = repository.getPrice(items);

        for(Product product : list) {
            product.setPrice(product.getPrice() * (100 - product.getDiscount()) / 100);
            System.out.println("Price Of " + product.getName() + " after " +
                    product.getDiscount() + "% discount is " + product.getPrice());
        }
        return list;
    }
}
