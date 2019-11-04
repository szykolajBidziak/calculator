package com.bibro;


public class Main {
    public static void main(String[] args) {
        String a = "2 * ( 23 + 3 )";
        RpnCalculator calculator = new RpnCalculator();

        System.out.println(calculator.solve(a));
    }
}
