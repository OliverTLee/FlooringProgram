/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Response;
import com.mycompany.flooringmastery.model.Tax;
import com.mycompany.flooringmastery.service.LocalDateFormatterTool;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author OliverTLee
 */
public class OrderDaoFileImpl extends FileDao<Order> implements OrderDao {

    String filePath;
    static final String HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    public OrderDaoFileImpl() {
        super(12, true);
    }

    @Override
    public Order findOrder(LocalDate date, int orderNumber) {
        filePath = "data/Orders_" + LocalDateFormatterTool.LocalDateToString(date) + ".txt";
        for (Order order : findOrdersByDate(date)) {
            if (order.getId() == orderNumber) {
                order.setDate(date);
                return order;
            }
        }
        return new Order();
    }

    @Override
    public List<Order> findOrdersByDate(LocalDate date) {
        filePath = "data/Orders_" + LocalDateFormatterTool.LocalDateToString(date) + ".txt";
        try {
            return read(this::mapToOrder, filePath).stream().collect(Collectors.toList());
        } catch (StorageException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Response addOrder(Order order) throws StorageException {
        Response response = new Response();
        filePath = "data/Orders_" + LocalDateFormatterTool.LocalDateToString(order.getDate()) + ".txt";
        if (order.getId() == 0) {
            order.setId(findNextId(order.getDate()));
        }
        append(order, this::mapToString, filePath, HEADER);

        return response;
    }

    @Override
    public Response updateOrder(Order order) throws StorageException {
        // can optimize this function to be a single file writing step instead of two.
        deleteOrder(order);
        return addOrder(order);
    }

    @Override
    public void deleteOrder(Order orderToRemove) throws StorageException {
        filePath = "data/Orders_" + LocalDateFormatterTool.LocalDateToString(orderToRemove.getDate()) + ".txt";
        List<Order> orders = findOrdersByDate(orderToRemove.getDate());
        orders.removeIf(order -> order.getId() == orderToRemove.getId());
        write(orders, this::mapToString, filePath);
    }

    private int findNextId(LocalDate date) {
        filePath = "data/Orders_" + LocalDateFormatterTool.LocalDateToString(date) + ".txt";
        int max = 0;
        for (Order order : findOrdersByDate(date)) {
            if (order.getId() > max) {
                max = order.getId();
            }
        }
        return max + 1;
    }

    private String mapToString(Order order) {
        return String.format("%s,%s,%s,%.2f,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f",
                order.getId(),
                order.getName(),
                order.getTax().getState(),
                order.getTax().getTaxRate(),
                order.getProduct().getProductType(),
                order.getArea(),
                order.getProduct().getMaterialCostPerSquareFoot(),
                order.getProduct().getLaborCostPerSquareFoot(),
                order.getMaterialCost(),
                order.getLaborCost(),
                order.getTaxCost(),
                order.getTotalCost());
    }

    private Order mapToOrder(String[] tokens) {
        Order order = new Order();
        Product product = new Product();
        Tax tax = new Tax();
        order.setId(Integer.parseInt(tokens[0]));
        order.setName(tokens[1]);
        tax.setState(tokens[2]);
        tax.setTaxRate(new BigDecimal(tokens[3]));
        product.setProductType(tokens[4]);
        order.setArea(new BigDecimal(tokens[5]));
        product.setMaterialCostPerSquareFoot(new BigDecimal(tokens[6]));
        product.setLaborCostPerSquareFoot(new BigDecimal(tokens[7]));
        order.setProduct(product);
        order.setTax(tax);
        return order;
    }
}
