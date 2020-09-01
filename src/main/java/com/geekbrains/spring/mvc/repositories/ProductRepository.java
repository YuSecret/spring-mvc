package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class ProductRepository {
    private List<Product> products;
    private Long maxId;
    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(17L, "Bob", 170L));
        this.products.add(new Product(27L, "John", 385L));
        this.maxId = 2L;
    }
    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Student not found");
    }
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product saveOrUpdateProduct(Product product) {
        if (product.getId() == null) {
            maxId++;
            product.setId(maxId);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("What???");
    }
}
