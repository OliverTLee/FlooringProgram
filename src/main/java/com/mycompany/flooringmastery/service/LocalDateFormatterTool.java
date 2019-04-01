/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.UI.BadDateFormatException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author OliverTLee
 */
public final class LocalDateFormatterTool {

    public static LocalDate stringToLocalDate(String dateString) throws BadDateFormatException {
        // potentially throws IndexOutOfBoundsException - if the string isn't the write size
        if (dateString.length() != 8) {
            throw new BadDateFormatException("Bad Length");
        }
        int month;
        int day;
        int year;
        try {
            month = Integer.parseInt(dateString.substring(0, 2));
            day = Integer.parseInt(dateString.substring(2, 4));
            year = Integer.parseInt(dateString.substring(4));
        } catch (NumberFormatException | DateTimeException e) {
            throw new BadDateFormatException("Not all numbers");
        }
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new BadDateFormatException(e.getMessage());
        }
    }

    public static String LocalDateToString(LocalDate date) {
        String[] tokens = date.toString().split("-");
        return tokens[1] + tokens[2] + tokens[0];
    }
}
