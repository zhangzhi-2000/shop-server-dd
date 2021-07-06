package com.neutech.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neutech.entity.Product;
import com.neutech.enumeration.ProductStatusEnum;
import com.neutech.enumeration.ResultExceptionEnum;
import com.neutech.form.ProductForm;
import com.neutech.mapper.CategoryMapper;
import com.neutech.mapper.ProductMapper;
import com.neutech.service.ProductService;
import com.neutech.vo.ProductVO;
import com.neutech.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResultVO listProduct(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> list=productMapper.listProduct();
        PageInfo pageInfo= PageInfo.of(list);
        //of方法只能用源数据，不然结果不对,但可以set重新定义
        List<ProductVO>productVOList=new ArrayList<>();
        for(Product product:list) {
            ProductVO productVO=new ProductVO();
            BeanUtils.copyProperties(product,productVO);

            ProductStatusEnum productStatusEnum=ProductStatusEnum.getProductStatusEnum(product.getStatus());
            productVO.setStatus(productStatusEnum);
            productVOList.add(productVO);
        }
        pageInfo.setList(productVOList);
        return ResultVO.success(pageInfo);
    }

    @Override
    public ResultVO addProduct(ProductForm productForm) {
        //数据校验
        if(ProductStatusEnum.getProductStatusEnum(productForm.getStatus())==null){
            return ResultVO.error(ResultExceptionEnum.DATA_NOT_EXISTS.getCode(), "状态值不合法");
        }
        if(categoryMapper.getOneById(productForm.getCategoryId())==null){
            return ResultVO.error(ResultExceptionEnum.DATA_NOT_EXISTS.getCode(),"类别不存在");
        }
        Product product=new Product();
        BeanUtils.copyProperties(productForm,product);
        //处理两个时间
        Date date=new Date();
        product.setCreateTime(date);
        product.setUpdateTime(date);

        int row=productMapper.save(product);
        return row>0?ResultVO.success():ResultVO.error(1004,"插入失败");
    }

    @Override
    public ResultVO updateProduct(ProductForm productForm) {
        if(productForm.getStatus()!=null && ProductStatusEnum.getProductStatusEnum(productForm.getStatus())==null){
            return ResultVO.error(ResultExceptionEnum.DATA_NOT_EXISTS.getCode(), "状态值不合法");
        }
        if(productForm.getCategoryId()!=null && categoryMapper.getOneById(productForm.getCategoryId())==null){
            return ResultVO.error(ResultExceptionEnum.DATA_NOT_EXISTS.getCode(),"类别不存在");
        }
        Product product=new Product();
        BeanUtils.copyProperties(productForm,product);
        product.setUpdateTime(new Date());

        int row=productMapper.update(product);
        return row>0?ResultVO.success():ResultVO.error(1004,"更新失败");
    }

    @Override
    public ResultVO deleteProduct(Integer[] ids) {
        int row=productMapper.deleteAllByIds(ids);
        return row>0?ResultVO.success():ResultVO.error(1004,"数据删除失败");
    }

    @Override
    public ResultVO getProductById(Integer id) {
        Product product=productMapper.getOneById(id);
        if(product==null){
            return ResultVO.error(1005,"商品不存在");
        }
        return ResultVO.success(product);
    }

    @Override
    public ResultVO getProductsByCategoryId(Integer categoryId) {
        List<Product>products=productMapper.getAllByCategoryId(categoryId);
        if(products.size()==0){
            return ResultVO.error(1005,"商品不存在");
        }
        return ResultVO.success(products);
    }
}
