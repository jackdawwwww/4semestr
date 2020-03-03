package Commands;

import java.util.List;
import Context.CalcContext;
import Exceptions.MyException;
import Exceptions.NegativeSQRT;


public class SqrtCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) throws MyException {
        Double x = context.pop();

        if (x >= 0) {
            x = Math.sqrt(x);
            context.push(x);
        }

        else throw new NegativeSQRT(x.toString());
    }
}
