package Context;

import Exceptions.ArgExceptions;
import Exceptions.StackExceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CalcContext {
    private Stack<Double> stack;
    private HashMap<String, Double> definitions;
    private List<String> comments;

    public CalcContext() {
        stack = new Stack<Double>();
        definitions = new HashMap<String, Double>();
    }

 //   public Stack<Double> getStack() {
 //       return stack;
 //   }

    public void setDefinitions(String name, Double value) {
        definitions.put(name, value);
    }

    public void setComments(String comment) {
        System.out.println("//" + comment + "/n");
    }

    public Double pop() throws StackExceptions {
        if (stack.isEmpty()) throw new StackExceptions("Stack is empty");
        return stack.pop();
    }

    public void push(Double value) {
        stack.push(value);
    }

    public void push(String  name) throws ArgExceptions {
        Double value = definitions.get(name);
        if (value == null) throw new ArgExceptions("Invalid arg");
        stack.push(value);
    }

    public void print() {
        System.out.println(stack.peek());
    }

}
