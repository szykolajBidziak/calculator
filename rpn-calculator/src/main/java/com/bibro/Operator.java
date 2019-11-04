package com.bibro;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum     Operator {

    LEFT_BRACKET("(", 0),
    RIGHT_BRACKET(")",1),
    MULTIPLY("*", 2),
    DIVIDE("/", 2),
    ADD("+", 1),
    SUBTRACT("-", 1),
    POWER("^", 3);

    private String operator;
    private int priority;

    Operator(String operator, int priority) {
        this.operator = operator;
        this.priority = priority;
    }

    public static int getPriority(String operator){
        return Arrays.stream(values())
                .filter(o -> o.operator.equals(operator))
                .findFirst()
                .map(value -> value.priority)
                .orElseThrow(NoSuchElementException::new);
    }
}
