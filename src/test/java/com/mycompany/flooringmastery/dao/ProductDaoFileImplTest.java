/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import java.math.BigDecimal;
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
public class ProductDaoFileImplTest {
    
    ProductDaoFileImpl dao = new ProductDaoFileImpl("data/Products.txt");
    public ProductDaoFileImplTest() {
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
     * Test of findAllProducts method, of class ProductDaoFileImpl.
     */
    @Test
    public void testFindAllProducts() {
        int size = dao.findAllProducts().size();
        assertEquals(4,size);
    }

    /**
     * Test of findProduct method, of class ProductDaoFileImpl.
     */
    @Test
    public void testFindProduct() {
        Product product = dao.findProduct("Wood");
        assertEquals(new BigDecimal(4.75),product.getLaborCostPerSquareFoot());
    }
    
}
