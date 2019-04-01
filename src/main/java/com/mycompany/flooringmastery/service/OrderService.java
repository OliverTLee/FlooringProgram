/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.StorageException;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Response;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public class OrderService {

    TaxService taxService;
    ProductService productService;
    OrderDao orderDao;

    public OrderService(TaxService taxService, ProductService productService, OrderDao orderDao) {
        this.taxService = taxService;
        this.productService = productService;
        this.orderDao = orderDao;
    }

    public boolean checkDependenciesAreGood() {
        // assert that the products and taxes files we want have length >0;
        List<Product> products = findAllProducts();
        List<Tax> taxes = findAllTaxes();
        return (products.size() > 0 && taxes.size() > 0);
    }

    public Result findOrder(Order orderFragment) {
        Result<Order> result = new Result();
        Order order = orderDao.findOrder(orderFragment.getDate(), orderFragment.getId());
        if (Validations.isFullOrder(order)) {
            result.setValue(order);
        } else {
            result.addError("Order could not be found.");
        }
        return result;
    }

    public Response addOrder(Order order) {
        Response response = new Response();
        if (order==null) {
            response.addError("Order was not added");
        }
        if (Validations.isFullOrder(order)) {
            try {
                response = orderDao.addOrder(order);
            } catch (StorageException ex) {
                response.addError("Sorry, failed to save order");
            }
        } else {
            response.addError("Not all fields completed properly");
        }
        return response;
    }

    public Response deleteOrder(Order order) {
        Response response = new Response();
        try {
            orderDao.deleteOrder(order);
        } catch (StorageException ex) {
            response.addError("Failed to delete Order, order not found");
        }
        return response;
    }

    public Response editOrder(Order order) {
        Response response = new Response();
        try {
            orderDao.updateOrder(order);
        } catch (StorageException ex) {
            response.addError("Failed to update Order, order not found");
        }
        return response;
    }

    public List<Order> findAllOrdersByDate(LocalDate date) {
        return orderDao.findOrdersByDate(date);
    }

    public Result findProduct(String productName) {
        return productService.findProduct(productName);
    }

    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    public Result findTax(String state) {
        return taxService.findTax(state);
    }

    public List<Tax> findAllTaxes() {
        return taxService.findAllTaxes();
    }

}
