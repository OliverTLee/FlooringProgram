/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.service.LocalDateFormatterTool;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author OliverTLee
 */
public class ProductDaoFileImpl extends FileDao<Product> implements ProductDao {

    String filePath;

    public ProductDaoFileImpl(String filePath) {
        super(3, true);
        this.filePath = filePath;
    }

    @Override
    public List<Product> findAllProducts() {
        try {
            return read(this::mapToProduct,filePath).stream().collect(Collectors.toList());
        } catch (StorageException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Product findProduct(String productType
    ) {
        for (Product possibleProduct : findAllProducts()) {
            if (possibleProduct.getProductType().equals(productType)) {
                return possibleProduct;
            }
        }
        return new Product();
    }
    //Currently unused - might be useful for writing to the file down the road.

    private String mapToString(Product product) {
        return String.format("%s,%.2f,%.2f",
                product.getProductType(),
                product.getMaterialCostPerSquareFoot(),
                product.getLaborCostPerSquareFoot());
    }

    private Product mapToProduct(String[] tokens) {
        Product prod = new Product();
        prod.setProductType(tokens[0]);
        prod.setMaterialCostPerSquareFoot(new BigDecimal(tokens[1]));
        prod.setLaborCostPerSquareFoot(new BigDecimal(tokens[2]));
        return prod;
    }


}

