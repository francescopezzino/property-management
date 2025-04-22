package com.mycompany.property_management.controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController();
        Double result = calculatorController.add(4.5, 8.5);
        System.out.println(result);
    }
}
