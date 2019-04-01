/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.model.Order;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Tax;

/**
 *
 * @author OliverTLee
 */
public final class Validations {

    static boolean isFullProduct(Product prod) {
        return !(prod ==null 
                ||prod.getLaborCostPerSquareFoot() == null
                || prod.getMaterialCostPerSquareFoot() == null
                || prod.getProductType() == null);
    }

    static boolean isFullTax(Tax tax) {
        return !( tax == null
                || tax.getState() == null 
                || tax.getTaxRate() == null 
                || tax.getState().trim().isEmpty());
    }

    //This statement DOES NOT check for ID, because it can be generated later.
    static boolean isFullOrder(Order order) {
        return !(order ==null
                || order.getTax() ==null
                || order.getProduct() ==null
                || !isFullTax(order.getTax())
                || !isFullProduct(order.getProduct())
                || order.getName() == null
                || order.getArea() == null
                || order.getDate() == null);
    }

}
