package Context;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CalcContext {
    private Stack<Double> stack;
    private HashMap<String, Double> definitions;
    private List<String> comments;

    CalcContext() {
        stack = new Stack<Double>();
        definitions = new HashMap<String, Double>();
    }

    public Stack<Double> getStack() {
        return stack;
    }

//    public HashMap<String, Double> getDefinitions() {
//        return definitions;
//    }
//
//    public List<String> getComments() { return comments; }

    public void setDefinitions(String name, Double value) {
        definitions.put(name, value);
    }

    public void setComments(String comment) {
        comments.add(comment);
    }

    public Double pop() {
        return stack.pop();
    }

    public void push(Double value) {
        stack.push(value);
    }

    public void push(String  name) {
        Double value = definitions.get(name);
        stack.push(value);
    }

    public void print() {
        System.out.println(stack.peek());
    }
}
