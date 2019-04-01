/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.TaxDao;
import com.mycompany.flooringmastery.model.Result;
import com.mycompany.flooringmastery.model.Tax;
import java.util.List;

/**
 *
 * @author OliverTLee
 */
public class TaxService {

    TaxDao taxDao;

    public TaxService(TaxDao taxDao) {
        this.taxDao = taxDao;
    }

    Result findTax(String state) {
        Result<Tax> result = new Result();
        Tax tax = taxDao.findTax(state);
        if (Validations.isFullTax(tax)) {
            result.setValue(taxDao.findTax(state));
        } else {
            result.addError("Could not find complete tax for given state");
        }
        return result;
    }

    List<Tax> findAllTaxes() {
        return taxDao.findAllTaxes();
    }
}
