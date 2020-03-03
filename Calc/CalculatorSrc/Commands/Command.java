package Commands;

import java.util.List;
import Context.CalcContext;
import Exceptions.MyException;


public interface Command {
    void execute(CalcContext context, List<String> args) throws MyException;
}
