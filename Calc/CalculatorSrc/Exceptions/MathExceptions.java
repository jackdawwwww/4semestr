package Exceptions;

public class MathExceptions extends Exception {
    public MathExceptions(String what) {
        super("MathException: " + what);
    }
}
