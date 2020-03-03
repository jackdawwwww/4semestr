package Commands;

import java.util.List;

import Context.CalcContext;
import Exceptions.MyException;

public class PlusCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) throws MyException {
        double y, x;

        y = context.pop();
        x = context.pop();

        context.push(x + y);
    }
}
