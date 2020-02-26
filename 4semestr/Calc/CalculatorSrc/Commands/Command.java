package Commands;

import java.util.ArrayList;
import Context.CalcContext;


public interface Command {

    public void execute(CalcContext context, ArrayList<String> args);
}
