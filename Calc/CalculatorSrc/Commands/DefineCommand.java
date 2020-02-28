package Commands;

import java.util.ArrayList;
import Context.CalcContext;


public class DefineCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        context.setDefinitions(args.get(0), Double.valueOf(args.get(1)));
    }

}
