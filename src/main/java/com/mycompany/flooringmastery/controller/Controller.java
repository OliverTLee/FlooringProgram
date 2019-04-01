/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.UI.View;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Response;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
import com.mycompany.flooringmastery.service.OrderService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public class Controller {

    View view;
    OrderService orderService;

    public Controller(View view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    public void run() {
        view.displayWelcome();
        if (!checkDependenciesAreGood()) {
            view.displayFailedToStart();
            System.exit(0);
        }
        MenuOptions selection;
        do {
            selection = view.getMainMenuChoice();
            switch (selection) {
                case DISPLAY_ORDERS:
                    displayOrders();
                    break;
                case ADD_ORDER:
                    addOrder();
                    break;
                case EDIT_ORDER:
                    editOrder();
                    break;
                case REMOVE_ORDER:
                    removeOrder();
                    break;

            }
        } while (selection != MenuOptions.EXIT);
        view.displayQuit();
    }

    public boolean checkDependenciesAreGood() {
        return orderService.checkDependenciesAreGood();
    }

    public void displayOrders() {
        LocalDate date = view.getDateFromUser();
        List<Order> orders = orderService.findAllOrdersByDate(date);
        view.displayOrders(orders);
    }

    public void addOrder() {
        List<Product> products = orderService.findAllProducts();
        List<Tax> taxes = orderService.findAllTaxes();
        view.displayAddOrderStart();
        Order order = view.addOrder(products, taxes);
        if (order == null) {
            return;
        }
        Response response = orderService.addOrder(order);
        view.displayOperationCompleted(response);
    }

    public void editOrder() {
        Order orderFragment = view.getDateAndOrderId();
        Result<Order> foundOrder = orderService.findOrder(orderFragment);
        if (foundOrder.hasError()) {
            view.displayErrors(foundOrder);
            return;
        }
        List<Product> products = orderService.findAllProducts();
        List<Tax> taxes = orderService.findAllTaxes();

        Order order = view.editOrder(foundOrder, products, taxes);
        if (order == null) {
            return;
        }
        Response response = orderService.editOrder(order);
        view.displayOperationCompleted(response);
    }

    public void removeOrder() {
        Order orderFragment = view.getDateAndOrderId();
        Result<Order> foundOrder = orderService.findOrder(orderFragment);
        if (foundOrder.hasError()) {
            view.displayErrors(foundOrder);
            return;
        }
        boolean shouldDelete = view.confirm(foundOrder.getValue());
        if (shouldDelete) {
            Response response = orderService.deleteOrder(orderFragment);
            view.displayOperationCompleted(response);
        }
    }

    public void quit() {

    }

}
