/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.TaxDao;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
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
public class TaxServiceTest {

    TaxDao daoMock = mock(TaxDao.class);
    TaxService service = new TaxService(daoMock);

    public TaxServiceTest() {
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
     * Test of findTax method, of class TaxService.
     */
    @Test
    public void testFindTaxSuccess() {
        Tax tax = new Tax();
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("15"));

        when(daoMock.findTax("OH")).thenReturn(tax);
        Result<Tax> found = service.findTax("OH");
        assertEquals(new BigDecimal("15.00"), found.getValue().getTaxRate());
    }

    @Test
    public void testFindTaxFail() {
        Tax tax = new Tax();
     

        when(daoMock.findTax("OH")).thenReturn(tax);
        Result<Tax> found = service.findTax("OH");
        assertNull(found.getValue());
    }
        @Test
    public void testFindTaxFailsOnWhiteSpace() {
        Tax tax = new Tax();
        tax.setState("   ");
        tax.setTaxRate(BigDecimal.ZERO);
        when(daoMock.findTax("OH")).thenReturn(tax);
        Result<Tax> found = service.findTax("OH");
        assertNull(found.getValue());
    }
    
}
