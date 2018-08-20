package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Leaf;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitExpressionParser implements Parser {

    private static HashMap<String, Integer> operationPriority;
    static {
        operationPriority = new HashMap<>();
        operationPriority.put("|", 12);
        operationPriority.put("&", 10);
        operationPriority.put("~", 3);
        operationPriority.put("^", 11);
        operationPriority.put(">>", 7);
        operationPriority.put("<<", 7);
        operationPriority.put(">>>", 7);
        operationPriority.put("<<<", 7);
    }

    private static String convertToPolishNotation(String expression) {

        String result = "";
        Stack<String> stack = new Stack<>();

        Pattern pattern = Pattern.compile(
                "^([0-9]+|\\||&|\\(|\\)|\\^|~|<<<|>>>|<<|>>)");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {

            String part = matcher.group();

            if (part.matches("[0-9]+")) {
                result = String.join(" ", result, part);

            } else {

                if (part.equals("~") || part.equals("(")) {
                    stack.push(part);
                } else {

                    if (part.equals(")")) {

                        while (true) {
                            part = stack.pop();

                            if (part.equals("(")) {
                                break;
                            }

                            result = String.join(" ", result, part);
                        }

                    } else {
                        String operation;

                        while (!stack.isEmpty()) {
                            operation = stack.pop();

                            if (operation.equals("(")
                                    || operationPriority.get(operation)
                                    > operationPriority.get(part)) {
                                stack.push(operation);
                                break;
                            }
                            result = String.join(" ", result, operation);
                        }
                        stack.push(part);
                    }
                }
            }

            expression = expression.substring(part.length());
            matcher = pattern.matcher(expression);
        }

        while (!stack.isEmpty()) {
            result = String.join(" ", result, stack.pop());
        }

        return result.trim();
    }

    public Component handleRequest(final String expression) {

        String polishExpression = convertToPolishNotation(expression);
        String calculatedExpression = calculateExpression(polishExpression);

        return new Leaf(calculatedExpression);
    }

    private static String calculateExpression(String expression) {
        String[] symbols = expression.split(" ");
        Stack<String> stack = new Stack<>();

        for (String symbol : symbols) {

            if (symbol.matches("[0-9]+")) {
                stack.push(symbol);

            } else {

                if (symbol.equals("~")) {
                    stack.push(String.valueOf(
                            ~Integer.parseInt(stack.pop())));
                    continue;
                }

                int operand1 = Integer.parseInt(stack.pop());
                int operand2 = Integer.parseInt(stack.pop());

                switch (symbol) {
                    case "&":
                        stack.push(String.valueOf(operand1 & operand2));
                        break;
                    case "|":
                        stack.push(String.valueOf(operand1 | operand2));
                        break;
                    case "^":
                        stack.push(String.valueOf(operand1 ^ operand2));
                        break;
                    case ">>":
                        stack.push(String.valueOf(operand2 >> operand1));
                        break;
                    case "<<":
                        stack.push(String.valueOf(operand2 << operand1));
                        break;
                    case ">>>":
                        stack.push(String.valueOf(operand2 >>> operand1));
                        break;
                    case "<<<":
                        stack.push(String.valueOf(operand2 << operand1));
                        break;
                    default:
                        // Exception
                        break;
                }
            }
        }
        return stack.pop();
    }
}
