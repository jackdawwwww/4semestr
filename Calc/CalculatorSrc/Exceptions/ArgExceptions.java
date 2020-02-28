package Exceptions;

public class ArgExceptions extends Exception {
    public ArgExceptions(String what) {
        super("ArgException: " + what);
    }
}
