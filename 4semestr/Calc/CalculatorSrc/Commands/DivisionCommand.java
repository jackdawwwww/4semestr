package Commands;

import java.util.ArrayList;

import Context.CalcContext;

public class DivisionCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        double y = 0.0, x = 0.0;

        y = context.pop();
        x = context.pop();
        context.push(x/y);
        //division by zero
    }
}
