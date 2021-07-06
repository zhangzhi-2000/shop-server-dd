package com.neutech.controller;

import com.neutech.entity.Product;
import com.neutech.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/listProduct")
    public List<Product> listProduct(){
        return productMapper.listProduct();
    }
}
