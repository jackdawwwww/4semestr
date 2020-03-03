package Commands;

import java.util.List;

import Context.CalcContext;
import Exceptions.DivisionByZero;
import Exceptions.MyException;


public class DivisionCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) throws MyException {
        double y, x;

        x = context.pop();
        y = context.pop();

        if ( x != 0 )
        context.push(y/x);

        else throw new DivisionByZero();
    }
}
