package Commands;

import java.util.List;
import Context.CalcContext;
import Exceptions.MyException;
import Exceptions.NoArgsExceptions;


public class DefineCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) throws MyException {
        if (args.size() < 2) throw new NoArgsExceptions();

        context.setDefinitions(args.get(0), args.get(1));
    }
}
