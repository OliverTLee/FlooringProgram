/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.UI;

import com.mycompany.flooringmastery.controller.MenuOptions;
import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Response;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
import com.mycompany.flooringmastery.service.LocalDateFormatterTool;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public class View {

    private ConsoleIO io = new ConsoleIO();

    public Order addOrder(List<Product> products, List<Tax> taxes) {
        Order order = new Order();
        order.setName(getNameFromUser());
        order.setArea(getAreaFromUser());
        order.setDate(getDateFromUser());
        order.setTax(pickTax(taxes));
        order.setProduct(pickProduct(products));
        if (confirm(order)) {
            return order;
        } else {
            return null;
        }
    }

    public Order editOrder(Result<Order> orderResult, List<Product> products, List<Tax> taxes) {
        Order order = orderResult.getValue();
        displayOrder(order);
        //want to edit :
        String newName = io.readString("Old name: " + order.getName() + " Enter new name, or hit enter to keep old name");
        newName.trim();
        if (newName != null && newName.length() > 0) {
            order.setName(newName);
        }
        String area = io.readString("Old area: " + order.getArea() + " Enter new Area, or hit enter to keep old area");
        area = area.trim();
        if (area != null && area.length() > 0) {
            try {
                order.setArea(new BigDecimal(area));
            } catch (NumberFormatException e) {

            }
        }

        io.print("Old State: " + order.getTax().getState());
        Tax newTax = pickTax(taxes);
        order.setTax(newTax);
        io.print("Old Product: " + order.getProduct().getProductType());
        Product product = pickProduct(products);
        order.setProduct(product);
        if (confirm(order)) {
            return order;
        }
        return null;
    }

    public Order getOrderToRemove() {
        io.print("Please enter info of an Order to delete");
        Order order = getDateAndOrderId();
        return order;
    }

    public boolean confirm(Order order) {
        displayOrder(order);
        boolean decision = io.readBoolean("Are you sure? (y/n)");
        if (decision == false) {
            io.print("Operation will not be performed.");
        }
        return decision;
    }

    public MenuOptions getMainMenuChoice() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MenuOptions mo : MenuOptions.values()) {
            io.print(String.format("%s. %s", mo.getValue(), mo.getName()));
            min = Math.min(mo.getValue(), min);
            max = Math.max(mo.getValue(), max);
        }
        int value = io.readInt(String.format("Select [%s-%s]:", min, max), min, max);
        return MenuOptions.fromValue(value);
    }

    public LocalDate getDateFromUser() {

        String userInput = io.readRequiredString("Please enter a date in MMDDYYYY format");
        LocalDate date;
        try {
            date = LocalDateFormatterTool.stringToLocalDate(userInput);
        } catch (BadDateFormatException e) {
            io.print("Date was input incorrectly, please try again");
            return getDateFromUser();
        }
        return date;
    }

    public Order getDateAndOrderId() {
        Order order = new Order();
        LocalDate date = getDateFromUser();
        order.setDate(date);
        int id = getIdFromUser();
        order.setId(id);
        return order;
    }

    private int getIdFromUser() {
        int out = io.readInt("Please enter an ID number, (1-1000) ", 0, 1001);
        return out;
    }

    private String getNameFromUser() {
        String name = io.readRequiredString("Please enter a customer name.");
        return name;
    }

    private BigDecimal getAreaFromUser() {
        BigDecimal area = io.readBigDecimal("Please enter an area greater than 0 (ft^2)");
        return area;
    }

    private Tax pickTax(List<Tax> taxes) {
        displayStates(taxes);
        int userSelection = io.readInt("Please enter a state (1-" + (taxes.size()) + ")", 1, (taxes.size()));
        // -1 is required because counting starts at 0 for the list
        return taxes.get(userSelection - 1);
    }

    private Product pickProduct(List<Product> products) {
        displayProducts(products);
        int userSelection = io.readInt("Please enter a Product (1-" + (products.size()) + ")", 1, (products.size()));
        // -1 is required because counting starts at 0 for the list
        return products.get(userSelection - 1);
    }

    public void displayMainMenu() {
        io.print(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print(" *  <<Flooring Program>>");
        io.print(" * 1. Display Orders");
        io.print(" * 2. Add an Order");
        io.print(" * 3. Edit an Order");
        io.print(" * 4. Remove an Order");
        io.print(" * 5. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public void displayQuit() {
        io.print("Exiting Program.");
    }

    public void displayOrders(List<Order> orders) {
        if (orders.size() == 0) {
            io.print("No orders were found for that date");
        }
        for (Order order : orders) {
            displayOrder(order);
        }
    }

    public void displayOrder(Order order) {
        try {
            io.print("Order Number:" + order.getId() + "|" + order.getName() + "|"
                    + order.getTax().getState() + "|" + order.getProduct().getProductType());
            io.print("Area: " + order.getArea()
                    + " | Material Cost/sq foot: " + order.getProduct().getMaterialCostPerSquareFoot()
                    + " | Labor Cost/sq foot: " + order.getProduct().getLaborCostPerSquareFoot());
            io.print("Materials: " + order.getMaterialCost()
                    + " | Labor: " + order.getLaborCost()
                    + " | Tax: " + order.getTaxCost()
                    + " | Total: " + order.getTaxCost());
            io.print("");
        } catch (NullPointerException e) {
            io.print("Empty field found in order: cannot display");
        }

    }

    public void displayOperationCompleted(Response response) {
        if (response.hasError()) {
            for (String error : response.getErrors()) {
                io.print(error);
            }
        } else {
            io.print("Operation successful");
        }
    }

    public void displayFailedToStart() {
        io.print("Failed to load all dependencies properly... Program will exit.");
    }

    public void displayWelcome() {
        io.print("");
        io.print("Welcome to Flooring Program, the pinnacle of console based floor applications!");
        io.print("");
    }

    private void displayStates(List<Tax> taxes) {
        int counter = 1;
        for (Tax tax : taxes) {
            io.print(counter + ". " + tax.getState());
            counter++;
        }
    }

    private void displayProducts(List<Product> products) {
        int counter = 1;
        for (Product product : products) {
            io.print(counter + ". " + product.getProductType());
            counter++;
        }
    }

    public void displayErrors(Response response) {
        for (String error : response.getErrors()) {
            System.out.println(error);
        }
    }

    public void displayAddOrderStart() {
        io.print("1: Display Orders by Date:");
    }
}
