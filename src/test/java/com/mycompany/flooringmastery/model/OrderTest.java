/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author OliverTLee
 */
public class OrderTest {

    Order order = new Order();

    public OrderTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTotalCost method, of class Order.
     */
    @Test
    public void testGetTotalCost() {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(new BigDecimal(5.15));
        product.setMaterialCostPerSquareFoot(new BigDecimal(4.75));
        product.setProductType("Pennies");

        Tax tax = new Tax();
        tax.setTaxRate(new BigDecimal(6.25));
        
        order.setArea(new BigDecimal(100));
        order.setProduct(product);
        order.setTax(tax);
        
        BigDecimal totalCost = order.getTotalCost();
                
        assertEquals(new BigDecimal(1051.88).setScale(2, RoundingMode.HALF_UP), totalCost);
    }

    /**
     * Test of getTaxCost method, of class Order.
     */
    @Test
    public void testGetTaxCost() {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(new BigDecimal(5.15));
        product.setMaterialCostPerSquareFoot(new BigDecimal(4.75));
        product.setProductType("Pennies");

        Tax tax = new Tax();
        tax.setTaxRate(new BigDecimal(6.25));
        
        order.setArea(new BigDecimal(100));
        order.setProduct(product);
        order.setTax(tax);
        // (1891) * .0655 = 123.86
        BigDecimal cost = order.getTaxCost();
        assertEquals(new BigDecimal(61.88).setScale(2, RoundingMode.HALF_UP), cost);
    }

    /**
     * Test of getMaterialCost method, of class Order.
     */
    @Test
    public void testGetMaterialCost() {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(new BigDecimal(100));
        product.setMaterialCostPerSquareFoot(new BigDecimal(22));
        product.setProductType("Pennies");

        order.setArea(new BigDecimal(15.5));
        order.setProduct(product);

        assertEquals(new BigDecimal(341).setScale(2), order.getMaterialCost());

    }

    /**
     * Test of getLaborCost method, of class Order.
     */
    @Test
    public void testGetLaborCost() {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(new BigDecimal(22));
        product.setMaterialCostPerSquareFoot(new BigDecimal(100));
        product.setProductType("Pennies");

        order.setArea(new BigDecimal(15.5));
        order.setProduct(product);

        assertEquals(new BigDecimal(341).setScale(2), order.getLaborCost());

    }

    @Test
    public void testGetSetArea() {
        order.setArea(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE.setScale(2), order.getArea());
    }
}
