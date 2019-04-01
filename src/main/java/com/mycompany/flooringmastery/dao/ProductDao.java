/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public interface ProductDao {
    
    public List<Product> findAllProducts();
    
    public Product findProduct(String productName);
    
}
