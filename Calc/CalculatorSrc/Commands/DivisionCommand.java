package Commands;

import java.util.ArrayList;

import Context.CalcContext;
import Exceptions.MathExceptions;
import Exceptions.StackExceptions;

public class DivisionCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) throws MathExceptions, StackExceptions {
        double y = 0.0, x = 0.0;

        y = context.pop();
        x = context.pop();

        if ( x == 0 ) throw new MathExceptions("Division by zero");
        context.push(x/y);
    }
}
