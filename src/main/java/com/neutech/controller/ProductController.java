package com.neutech.controller;

import com.neutech.entity.Product;
import com.neutech.enumeration.ResultExceptionEnum;
import com.neutech.form.ProductForm;
import com.neutech.service.ProductService;
import com.neutech.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/getProductById")
    public ResultVO getProductById(Integer id){
        return productService.getProductById(id);
    }
    @GetMapping("/getProductsByCategoryId")
    public ResultVO getProductsByCategoryId(Integer categoryId){
        return productService.getProductsByCategoryId(categoryId);
    }
    @GetMapping("/listProduct")
    public ResultVO listProduct(@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        ResultVO resultVO=productService.listProduct(pageNum,pageSize);
        return productService.listProduct(pageNum,pageSize);
    }
    @PostMapping("/addProduct")
    public ResultVO addProduct(@RequestBody  @Valid ProductForm productForm, BindingResult bindingResult){
        System.out.println(productForm);
        if(bindingResult.getErrorCount()>0){
            return ResultVO.error(ResultExceptionEnum.FORMAT_EXCEPTION.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return productService.addProduct(productForm);
    }

    @PostMapping("updateProduct")
    public ResultVO updateProduct(@RequestBody ProductForm productForm){
//        System.out.println(productForm);
//        if(bindingResult.getErrorCount()>0){
//            return ResultVO.error(ResultExceptionEnum.FORMAT_EXCEPTION.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
        if(productForm.getId()==null){
            return ResultVO.error(ResultExceptionEnum.FORMAT_EXCEPTION.getCode(), "id不能为空");
        }
        return productService.updateProduct(productForm);
    }
    @GetMapping("/deleteProduct")
    public ResultVO deleteProduct(@RequestParam(value = "id")Integer[]ids){
       return  productService.deleteProduct(ids);
    }



}
