package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Leaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing bit expressions.
 */
public class BitExpressionParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NotBitExpressionLexemeParser.class);

    /**
     * This map stores information about the priority of the bit operations.
     */
    private static HashMap<String, Integer> operationPriority;
    static {
        operationPriority = new HashMap<>();
        operationPriority.put("|", 5);
        operationPriority.put("&", 3);
        operationPriority.put("~", 1);
        operationPriority.put("^", 4);
        operationPriority.put(">>", 2);
        operationPriority.put("<<", 2);
        operationPriority.put(">>>", 2);
        operationPriority.put("<<<", 2);
    }

    /**
     * This method creates an instance of {@link Component} that stores
     * calculated value of an input bit expression.
     * @param expression if a bit expression that should be parsed.
     * @return {@link Component} instance containing bit expression value.
     */
    public Component handleRequest(final String expression) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Bit expression "
                    + "'" + expression + "'"
                    + " is being parsed.");
        }

        String polishExpression = convertToPolishNotation(expression);
        String calculatedExpression = calculateExpression(polishExpression);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Component "
                    + "'" + calculatedExpression + "'" + " added");
        }

        return new Leaf(calculatedExpression);
    }

    /**
     * This method converts a bit expression into the polish notation.
     * @param expression is an input bit expression.
     * @return bit expression in polish notation.
     */
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

    /**
     * This method calculates the value of bit expression.
     * @param expression is a bit expression in polish notation.
     * @return the value of bit expression.
     */
    private static String calculateExpression(final String expression) {
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
                        break;
                }
            }
        }
        return stack.pop();
    }
}
