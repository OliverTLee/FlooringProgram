/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author OliverTLee
 */
public class TaxDaoFileImpl extends FileDao<Tax> implements TaxDao {

    String filePath;

    public TaxDaoFileImpl(String filePath) {
        super(2, true);
        this.filePath = filePath;
    }

    @Override
    public List<Tax> findAllTaxes() {
        try {
            return read(this::mapToTax, filePath).stream().collect(Collectors.toList());
        } catch (StorageException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Tax findTax(String state) {
        for (Tax possibleTax : findAllTaxes()) {
            if (possibleTax.getState().equals(state)) {
                return possibleTax;
            }
        }
        return new Tax();
    }

    //Currently unused - might be useful for writing to the file down the road.
    private String mapToString(Tax tax) {
        return String.format("%s,%.2f", tax.getState(), tax.getTaxRate());
    }

    private Tax mapToTax(String[] tokens) {
        Tax tax = new Tax();
        tax.setState(tokens[0]);
        tax.setTaxRate(new BigDecimal(tokens[1]));
        return tax;
    }
}
