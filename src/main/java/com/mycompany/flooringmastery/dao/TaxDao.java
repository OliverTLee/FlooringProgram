/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Tax;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public interface TaxDao {
    
    public List<Tax> findAllTaxes();
    
    public Tax findTax(String state);
}
