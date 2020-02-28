package Commands;

import java.util.ArrayList;
import Context.CalcContext;
import Exceptions.ArgExceptions;
import Exceptions.MathExceptions;
import Exceptions.StackExceptions;


public interface Command {

    public void execute(CalcContext context, ArrayList<String> args) throws MathExceptions, StackExceptions, ArgExceptions;
}
