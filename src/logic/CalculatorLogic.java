package logic;

public class CalculatorLogic {
    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Divisione per zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Operatore non valido: " + operator);
        }
    }
}
