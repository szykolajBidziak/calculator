package com.bibro;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Operation {

    ADD("+") {
        @Override
        double apply(double a, double b) { return a + b; }
    },
    SUBTRACT("-") {
        @Override
        double apply(double a, double b) { return a - b; }
    },
    MULTIPLY("*") {
        @Override
        double apply(double a, double b) {
            return a * b;
        }
    },
    POWER("^") {
        @Override
        double apply(double a, double b) {
            return Math.pow(a, b);
        }
    },
    DIVIDE("/") {
        @Override
        double apply(double a, double b) {
            return a / b;
        }
    };

    private String operator;

    Operation(String operator) {
        this.operator = operator;
    }

    public static Operation getByOperator(String operation) {
        return Arrays.stream(values())
                .filter(o -> o.operator.equals(operation))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    abstract double apply(double a, double b);
}
