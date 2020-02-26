package Commands;

import java.util.ArrayList;
import Context.CalcContext;

public class SqrtCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        Double x = Math.sqrt(context.pop());
        context.push(x);
    }
}
