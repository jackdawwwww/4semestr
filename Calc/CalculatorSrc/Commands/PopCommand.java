package Commands;

import java.util.ArrayList;

import Context.CalcContext;
import Exceptions.StackExceptions;

public class PopCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) throws StackExceptions {
        context.pop();
    }
}
