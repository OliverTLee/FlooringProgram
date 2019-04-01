/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Result;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
public class OrderServiceTest {
    
    OrderDao daoMock = mock(OrderDao.class);
    TaxService taxMock = mock(TaxService.class);
    ProductService productMock = mock(ProductService.class);
    OrderService orderService = new OrderService(taxMock,productMock,daoMock);
    
    public OrderServiceTest() {
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
     * Test of findOrder method, of class OrderService.
     */
    @Test
    public void testFindOrderSuccess() {
        Order order = new Order();
        order = order.fillAllFieldsWithStock();
        order.setArea(BigDecimal.ONE);
        
        when(daoMock.findOrder(order.getDate(), order.getId())).thenReturn(order);
        
        Result<Order> result = orderService.findOrder(order);
        assertEquals(result.getValue().getId(),order.getId());
    }
        @Test
    public void testFindOrderFail() {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setArea(BigDecimal.ONE);
        
        when(daoMock.findOrder(order.getDate(), 1)).thenReturn(order);
        
        Result<Order> result = orderService.findOrder(order);
        assertTrue(result.hasError());
    }

    /**
     * Test of findAllOrdersByDate method, of class OrderService.
     */
    @Test
    public void testFindAllOrdersByDateSuccess() {
        Order order = new Order();
        order.fillAllFieldsWithStock();
        Order order2 = new Order();
        order.setName("Ted");
        List<Order> list = new ArrayList();
        list.add(order);
        list.add(order2);
        when(daoMock.findOrdersByDate(LocalDate.now())).thenReturn(list);
        List<Order> listFound = orderService.findAllOrdersByDate(LocalDate.now());
        assertEquals(2, listFound.size());
        
    }

    /**
     * Test of findProduct method, of class OrderService.
     */
    

    
}
