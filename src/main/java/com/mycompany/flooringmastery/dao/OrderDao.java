/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Response;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public interface OrderDao {

    public Order findOrder(LocalDate date, int orderNumber);

    public List<Order> findOrdersByDate(LocalDate date);
    
    public Response addOrder(Order order) throws StorageException;
    
    public void deleteOrder(Order order) throws StorageException;
    
    public Response updateOrder(Order order) throws StorageException;
}
