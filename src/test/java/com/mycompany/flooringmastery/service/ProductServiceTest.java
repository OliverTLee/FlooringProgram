/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.ProductDaoFileImpl;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Result;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author OliverTLee
 */
public class ProductServiceTest {

    ProductDao daoMock = mock(ProductDao.class);
    ProductService service = new ProductService(daoMock);

    public ProductServiceTest() {
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
     * Test of findProduct method, of class ProductService.
     */
    @Test
    public void testFindProductGood() {
        Product prod = new Product();
        prod.setProductType("Glass");
        prod.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        prod.setMaterialCostPerSquareFoot(BigDecimal.ZERO);

        when(daoMock.findProduct("Glass")).thenReturn(prod);
        Result<Product> found = service.findProduct("Glass");

        assertEquals(found.getValue().getProductType(), prod.getProductType());
    }

    @Test
    public void testFindProductFail() {

        Product prod2 = new Product();
        when(daoMock.findProduct("Glass")).thenReturn(prod2);
        Result<Product> found = service.findProduct("Glass");

        assertNull(found.getValue());
    }

}
