package Exceptions;

public class InvalidCommandExceptions extends ComExceptions {

    public InvalidCommandExceptions(String what) {
        super("Invalid command " + what);
    }
}