/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Response;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
public class OrderDaoFileImplTest {

    OrderDaoFileImpl dao = new OrderDaoFileImpl();

    public OrderDaoFileImplTest() {
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
     * Test of findOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testFindOrderNumber() {
        Order order = dao.findOrder(LocalDate.of(2013, 06, 01), 1);
        assertEquals(1, order.getId());
        assertEquals("Wood", order.getProduct().getProductType());
    }

    @Test
    public void testFindOrderTotalCost() {
        Order order = dao.findOrder(LocalDate.of(2013, 06, 01), 1);
        assertEquals(order.getTotalCost(), new BigDecimal("1051.88"));
    }

    @Test
    public void testFindOrderCannotFind() {
        Order order = dao.findOrder(LocalDate.now(), 0);
        assertNull(order.getArea());
    }

    /**
     * Test of findOrdersByDate method, of class OrderDaoFileImpl.
     */
    @Test
    public void testFindOrdersByDate() throws StorageException {

        LocalDate date = LocalDate.of(2000, 01, 01);
        Order order = new Order();
        order = order.fillAllFieldsWithStock();
        int initialSize = dao.findOrdersByDate(date).size();
        dao.addOrder(order);
        int afterSize = dao.findOrdersByDate(date).size();
        assertEquals(initialSize+1,afterSize);
    }

    /**
     * Test of addOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testAddAndDeleteOrder() throws StorageException {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(BigDecimal.TEN);
        product.setMaterialCostPerSquareFoot(BigDecimal.ZERO);
        product.setProductType("Wood");

        Tax tax = new Tax();
        tax.setState("Minnesota");
        tax.setTaxRate(BigDecimal.ZERO);
        
        LocalDate date = (LocalDate.of(2013, 06, 01));
        Order order = new Order();
        order.setArea(BigDecimal.ONE);
        order.setDate(date);
        order.setName("Mark");
        order.setProduct(product);
        order.setTax(tax);
        
        dao.addOrder(order);
        assertEquals(BigDecimal.ONE.setScale(2), dao.findOrder(date, 2).getArea());
         dao.deleteOrder(order);
    }

    /**
     * Test of updateOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testUpdateOrder() throws StorageException {
        LocalDate date = (LocalDate.of(2013, 06, 01));
        Order order = dao.findOrder(date, 2);
        Tax tax = order.getTax();
        tax.setState("Minn");
        order.setTax(tax);
        dao.updateOrder(order);
        Order after = dao.findOrder(date, order.getId());
        assertEquals(after.getName(),order.getName());
    }

    /**
     * Test of deleteOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testDeleteOrder() {
    }

}
