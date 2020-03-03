package Exceptions;

public class MathExceptions extends MyException {
    public MathExceptions(String what) {
        super("MathException: " + what);
    }
}


