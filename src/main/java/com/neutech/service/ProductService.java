package com.neutech.service;

import com.neutech.form.ProductForm;
import com.neutech.vo.ResultVO;

public interface ProductService {
    ResultVO listProduct(Integer pageNum,Integer PageSize);

    ResultVO addProduct(ProductForm productForm);

    ResultVO updateProduct(ProductForm productForm);

    ResultVO deleteProduct(Integer[]ids);
    ResultVO getProductById(Integer id);
    ResultVO getProductsByCategoryId(Integer categoryId);
}
