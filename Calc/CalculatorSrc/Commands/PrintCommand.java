package Commands;

import java.util.List;
import Context.CalcContext;

public class PrintCommand implements Command {

    @Override
    public void execute(CalcContext context, List<String> args) {
       context.print();
    }
}
