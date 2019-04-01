/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author OliverTLee
 */
public class Product {
    
    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSqaureFoot;
    
    public Product() {
        
    }
    public Product(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSqaureFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSqaureFoot) {
        this.laborCostPerSqaureFoot = laborCostPerSqaureFoot.setScale(2, RoundingMode.HALF_UP);
    }
    
    
}
