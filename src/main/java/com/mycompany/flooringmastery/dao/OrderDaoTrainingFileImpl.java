/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Response;

/**
 *
 * @author OliverTLee
 */
public class OrderDaoTrainingFileImpl extends OrderDaoFileImpl {

    @Override
    public void deleteOrder(Order orderToRemove) throws StorageException {
        return;
    }

    @Override
    public Response updateOrder(Order order) throws StorageException {
        Response response = new Response();
        response.addError("Cannot edit files in training mode");
        return new Response();
    }

    @Override
    public Response addOrder(Order order) throws StorageException {
        Response response = new Response();
        response.addError("Cannot edit files in training mode");
        return new Response();
    }
}
