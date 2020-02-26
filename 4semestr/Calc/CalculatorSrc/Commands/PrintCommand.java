package Commands;

import java.util.ArrayList;

import Context.CalcContext;

public class PrintCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
       context.print();
    }
}
