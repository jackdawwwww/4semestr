package Exceptions;

public class InvalidArgsExceptions extends ArgExceptions {

    public InvalidArgsExceptions(String what) {
        super("invalid arg " + what);
    }
}