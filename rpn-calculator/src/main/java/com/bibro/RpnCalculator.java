package com.bibro;

import java.util.Stack;

public class RpnCalculator implements Calculator {

    public double solve(String equation) {
        equation = convertEquationToRpn(equation);
        String[] parsedEquation = equation.split(" ");
        Stack<String> stack = new Stack<>();
        double a;
        double b;

        for (String it : parsedEquation) {
            switch (it) {
                case ("/"):
                case ("*"):
                case ("-"):
                case ("+"):
                case ("^"):
                    a = Double.parseDouble(stack.pop());
                    b = Double.parseDouble(stack.pop());
                    stack.push(String.valueOf(Operation.getByOperator(it).apply(b, a)));
                    break;
                default:
                    stack.push(it);
                    break;
            }
        }
        return Double.valueOf(stack.peek());
    }

    private String convertEquationToRpn(String equation) {
        String output = "";
        Stack<String> stack = new Stack<>();
        String[] parsedEquation = equation.split(" ");

        for (String it : parsedEquation) {
            switch (it) {
                case ("/"):
                case ("*"):
                case ("-"):
                case ("+"):
                case ("^"):
                    output = operatorCase(stack, output, it);
                    break;
                case (")"):
                    output = rightBracketCase(stack, output);
                    break;
                case ("("):
                    stack.push(it);
                    break;
                default:
                    output = output.concat(it + " ");
                    break;
            }
        }

        while (!stack.isEmpty()) {
            output = output.concat(stack.pop() + " ");
        }
        return output;
    }

    private String rightBracketCase(Stack<String> stack, String output) {
        String newOutput = output;

        while (!stack.peek().equals(("("))) {
            newOutput = newOutput.concat(stack.pop() + " ");
        }
        stack.pop();

        return newOutput;
    }

    private String operatorCase(Stack<String> stack, String output, String operator) {
        String newOutput = output;

        if (stack.isEmpty() || Operator.getPriority(operator) > Operator.getPriority(stack.peek())) {
            stack.push(operator);
        } else {
            while (stack.size() != 0 && Operator.getPriority(stack.peek()) >= Operator.getPriority(operator)) {
                newOutput = newOutput.concat(stack.pop() + " ");
            }
            stack.push(operator);
        }
        return newOutput;
    }
}
