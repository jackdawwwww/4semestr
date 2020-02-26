package Commands;

import java.util.ArrayList;

import Context.CalcContext;

public class PushCommand implements Command{

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        try {
            Double x = Double.parseDouble(args.get(0));
            context.push(x);
        } catch (NumberFormatException e) {
            context.push(args.get(0));
        }
    }
}
