package Context;

import Exceptions.ArgExceptions;
import Exceptions.EmptyStackExceptions;
import Exceptions.InvalidArgsExceptions;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class CalcContext {
    private Stack<Double> stack;
    private HashMap<String, Double> definitions;

    public CalcContext() {
        stack = new Stack<>();
        definitions = new HashMap<>();
    }

    public void setDefinitions(String name, String value) throws InvalidArgsExceptions {
        double num;
        try {
            num = Double.parseDouble(value);
            definitions.put(name, num);
        } catch (NumberFormatException e) {
            throw new InvalidArgsExceptions("DEFINE " + value);
        }
    }

    public Double pop() throws EmptyStackExceptions {
        if (stack.isEmpty()) throw new EmptyStackExceptions();

        return stack.pop();
    }

    public void push(Double value) {
        stack.push(value);
    }

    public void push(String  name) throws ArgExceptions {
        Double  value;

        try {
            value = Double.parseDouble(name);
            push(value);
        } catch (NumberFormatException e) {
            value = definitions.get(name);

            if (value == null) throw new InvalidArgsExceptions("DEFINE null");
            stack.push(value);
        }
    }

    public boolean isEmpty() { return stack.isEmpty(); }

    public int size() { return stack.size(); }

    public void print() {
        if (stack.isEmpty()) throw new EmptyStackException();
        System.out.println(stack.peek());
    }


}
