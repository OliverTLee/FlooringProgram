/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author OliverTLee
 */
public class Order {

    private LocalDate date;
    private int id;
    private String name;
    private Tax tax;
    private Product product;
    private BigDecimal area;

    public Order fillAllFieldsWithStock() {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        product.setMaterialCostPerSquareFoot(BigDecimal.ZERO);
        product.setProductType("Material");

        Tax tax = new Tax();
        tax.setState("State");
        tax.setTaxRate(BigDecimal.ZERO);
        
        LocalDate date = (LocalDate.of(2000, 01, 01));
        Order order = new Order();
        order.setArea(BigDecimal.ZERO);
        order.setDate(date);
        order.setName("Name");
        order.setProduct(product);
        order.setTax(tax);
        return order;
    }
    public Order() {
        
    }
    public Order(String name, BigDecimal area) {
        this.name = name;
        this.area = area;
    }
    
    
    public BigDecimal getTotalCost() {
        BigDecimal materialCost =product.getMaterialCostPerSquareFoot().multiply(area);
        BigDecimal laborCost = product.getLaborCostPerSquareFoot().multiply(area);
        BigDecimal totalPreTax = materialCost.add(laborCost);
        BigDecimal taxAsDecimal = tax.getTaxRate().divide(new BigDecimal(100))
                .setScale(4,RoundingMode.HALF_UP);
        BigDecimal taxCost = totalPreTax.multiply(taxAsDecimal);
        return materialCost.add(laborCost).add(taxCost).setScale(2,RoundingMode.HALF_UP);
    }
    public BigDecimal getTaxCost() {
        BigDecimal materialCost =product.getMaterialCostPerSquareFoot().multiply(area);
        BigDecimal laborCost = product.getLaborCostPerSquareFoot().multiply(area);
        BigDecimal totalPreTax = materialCost.add(laborCost);
        BigDecimal taxAsDecimal = tax.getTaxRate().divide(new BigDecimal(100))
                .setScale(4,RoundingMode.HALF_UP);
        return totalPreTax.multiply(taxAsDecimal).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getMaterialCost() {
        return product.getMaterialCostPerSquareFoot().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getLaborCost() {
        return product.getLaborCostPerSquareFoot().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area.setScale(2);
    }
    
}
