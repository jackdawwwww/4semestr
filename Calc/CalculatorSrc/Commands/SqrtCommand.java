package Commands;

import java.util.ArrayList;
import Context.CalcContext;
import Exceptions.StackExceptions;

public class SqrtCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) throws StackExceptions {
        Double x = context.pop();
        if (x < 0) throw new StackExceptions("SQRT from negative number");
        x = Math.sqrt(x);
        context.push(x);
    }
}
