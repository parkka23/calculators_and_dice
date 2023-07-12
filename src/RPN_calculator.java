import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;

public class RPN_calculator {
    public Double calculate(String[] tokens) {
        Stack<String> stack = new Stack<String>();
        BigDecimal x, y;
        String result = "";
        String choice;
        BigDecimal value;
        String p = "";

        // Iterating to each character
        // in the array of the string
        for (int i = 0; i < tokens.length; i++) {

            // If the character is not the special character
            // ('+', '-' ,'*' , '/')
            // then push the character to the stack
            if (!tokens[i].equals("+") && !tokens[i].equals("-")
                    && !tokens[i].equals("*") && !tokens[i].equals("/")) {
                stack.push(tokens[i]);
                continue;
            } else {
                // else if the character is the special
                // character then use the switch method to
                // perform the action
                choice = tokens[i];


                // Switch-Case
                switch (choice) {
                    case "+":

                        // Performing the "+" operation by popping
                        // put the first two character
                        // and then again store back to the stack

                        x = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        y = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        value = x.add(y);
                        result = p + value;
                        stack.push(result);
                        break;

                    case "-":

                        // Performing the "-" operation by popping
                        // put the first two character
                        // and then again store back to the stack
                        x = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        y = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        value = y.subtract(x);
                        result = p + value;
                        stack.push(result);
                        break;

                    case "*":

                        // Performing the "*" operation
                        // by popping put the first two character
                        // and then again store back to the stack

                        x = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        y = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        value = x.multiply(y);
                        result = p + value;
                        stack.push(result);
                        break;

                    case "/":

                        // Performing the "/" operation by popping
                        // put the first two character
                        // and then again store back to the stack

                        x = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        y = BigDecimal.valueOf(Double.parseDouble(stack.pop()));
                        value = y.divide(x);
                        result = p + value;
                        stack.push(result);
                        break;

                    default:
                        continue;
                }
            }
        }

        // Method to convert the String to integer
        return Double.parseDouble(stack.pop());
    }
}

class Test {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = " ";

        do {
            // String Input
            System.out.print(">> ");
            input = sc.nextLine();
            String[] s = input.split(" ");

            RPN_calculator str = new RPN_calculator();
            Double result = str.calculate(s);
            System.out.println(result);
            System.out.println();

        } while (!input.equals("x"));
    }
}