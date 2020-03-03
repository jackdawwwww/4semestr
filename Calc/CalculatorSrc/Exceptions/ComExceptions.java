package Exceptions;

public class ComExceptions extends MyException {
    public ComExceptions(String what) {
        super("Command Exception: " + what);
    }
}



