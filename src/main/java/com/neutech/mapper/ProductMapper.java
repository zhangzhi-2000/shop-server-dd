package com.neutech.mapper;

import com.neutech.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> listProduct();
    int save(Product product);
    int update(Product product);
    int deleteAllByIds(Integer []ids);
    Product getOneById(Integer id);
    List<Product> getAllByCategoryId(Integer CategoryId);
}
