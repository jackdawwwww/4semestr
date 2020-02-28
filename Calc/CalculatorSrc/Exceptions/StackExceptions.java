package Exceptions;

public class StackExceptions extends Exception{
    public StackExceptions(String what) {
        super("StackException: " + what);
    }
}
