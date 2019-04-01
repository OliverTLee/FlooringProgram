/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Result;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public class ProductService {
    
    ProductDao productDao ;
    
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    Result findProduct(String productName) {
        Result<Product> result = new Result();
        Product prod = productDao.findProduct(productName);
        if (Validations.isFullProduct(prod)) {
            result.setValue(prod);
        } else {
            result.addError("Product matching that name not found.");
        }
        return result;
    }

    List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }
}
