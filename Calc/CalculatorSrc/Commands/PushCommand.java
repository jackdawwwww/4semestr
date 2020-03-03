package Commands;

import java.util.List;
import Context.CalcContext;
import Exceptions.MyException;

public class PushCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) throws MyException {
        context.push(args.get(0));
    }
}
