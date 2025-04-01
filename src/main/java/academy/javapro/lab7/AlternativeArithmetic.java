/* Project: Alternative Arithmetic Operations in Java
 * Class: AlternativeArithmetic.java
 * Author: MEHMET SOYDAN
 * Date: 03/31/2025
 * This program implements addition and division operations without using
 * their conventional operators, demonstrating alternative approaches using bitwise operations.
 */
package academy.javapro.lab7;

public class AlternativeArithmetic {

    // ---------------------------------------------------------------
    // Adds two integers without using the '+' operator
    public static int addWithoutPlus(int a, int b) {
        while (b != 0) {
            int carry = a & b;  // Calculate carry bits
            a = a ^ b;          // Sum without carry
            b = carry << 1;     // Shift carry to left for next iteration
        }
        return a;
    }

    // ---------------------------------------------------------------
    // Divides two integers without using the '/' operator
    public static int divideWithoutDivideOperator(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }

        // Handle negative numbers
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int quotient = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }

        return isNegative ? -quotient : quotient;
    }

    // ---------------------------------------------------------------
    // Main method to test the implementations
    public static void main(String[] args) {
        testAddition();
        testDivision();
    }

    // ---------------------------------------------------------------
    // Tests the addWithoutPlus method with various cases
    private static void testAddition() {
        System.out.println("Testing addition without '+' operator:");
        
        testAddCase(5, 3, 8);
        testAddCase(-2, 7, 5);
        testAddCase(0, 0, 0);
        testAddCase(-5, -3, -8);
        testAddCase(100, 200, 300);
        testAddCase(Integer.MAX_VALUE, 1, Integer.MIN_VALUE);
        testAddCase(-100, 100, 0);
    }

    // ---------------------------------------------------------------
    // Tests the divideWithoutDivideOperator method with various cases
    private static void testDivision() {
        System.out.println("\nTesting division without '/' operator:");
        
        testDivideCase(10, 2, 5);
        testDivideCase(15, 3, 5);
        testDivideCase(8, 4, 2);
        testDivideCase(7, 2, 3);
        testDivideCase(100, 10, 10);
        testDivideCase(-15, 3, -5);
        testDivideCase(15, -3, -5);
        testDivideCase(0, 5, 0);
        testDivideCase(1024, 2, 512);
        
        // Test division by zero
        try {
            divideWithoutDivideOperator(10, 0);
            System.out.println("10 / 0 = (Should not reach here)");
        } catch (ArithmeticException e) {
            System.out.println("10 / 0 = " + e.getMessage() + " (Correct)");
        }
    }

    // ---------------------------------------------------------------
    // Helper method to test addition cases
    private static void testAddCase(int a, int b, int expected) {
        int result = addWithoutPlus(a, b);
        System.out.printf("%d + %d = %d (%s)%n", 
                         a, b, result, 
                         result == expected ? "Correct" : "Incorrect");
    }

    // ---------------------------------------------------------------
    // Helper method to test division cases
    private static void testDivideCase(int dividend, int divisor, int expected) {
        try {
            int result = divideWithoutDivideOperator(dividend, divisor);
            System.out.printf("%d / %d = %d (%s)%n", 
                            dividend, divisor, result,
                            result == expected ? "Correct" : "Incorrect");
        } catch (ArithmeticException e) {
            System.out.printf("%d / %d = %s (Correct)%n", 
                            dividend, divisor, e.getMessage());
        }
    }
}
