package Exceptions;

public class ArgExceptions extends MyException {
    public ArgExceptions(String what) {
        super("ArgException: " + what);
    }
}


