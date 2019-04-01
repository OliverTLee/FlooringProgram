/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

/**
 *
 * @author OliverTLee
 */
public enum MenuOptions {
    DISPLAY_ORDERS(1, "Display"),
    ADD_ORDER(2, "Add"),
    EDIT_ORDER(3, "Edit"),
    REMOVE_ORDER(4, "Remove"),
    EXIT(5, "Exit");

    private final int value;
    private final String name;

    private MenuOptions(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static MenuOptions fromValue(int value) {
        for (MenuOptions mo : MenuOptions.values()) {
            if (mo.getValue() == value) {
                return mo;
            }
        }
        return EXIT;
    }
}
