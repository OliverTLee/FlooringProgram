/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Tax;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaxDaoFileImplTest {
    
    TaxDaoFileImpl dao = new TaxDaoFileImpl("data/Taxes.txt");
    public TaxDaoFileImplTest() {
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
     * Test of findAllTaxes method, of class TaxDaoFileImpl.
     */
    @Test
    public void testFindAllTaxes() {
        int size = dao.findAllTaxes().size();
        assertEquals((4),size);
    }

    /**
     * Test of findTax method, of class TaxDaoFileImpl.
     */
    @Test
    public void testFindTax() {
        Tax tax = dao.findTax("OH");
        BigDecimal rate = tax.getTaxRate();
        assertEquals(new BigDecimal("6.25"),rate);
    }
    
}
