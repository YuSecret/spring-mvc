package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "all_products";
    }
    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }
    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Product newProduct) {
        productRepository.saveOrUpdateProduct(newProduct);
        return "redirect:/products/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Product modifiedProduct) {
        productRepository.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping("/json2/{id}")
    @ResponseBody
    public Product showJsonProducts(@PathVariable Long id) {
        return productRepository.findById(id);
    }
}
