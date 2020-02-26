package Commands;

import java.util.ArrayList;

import Context.CalcContext;

public class PlusCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        double y = 0.0, x = 0.0;

        y = context.POP();
        x = context.POP();
        context.PUSH(x + y);
    }
}
