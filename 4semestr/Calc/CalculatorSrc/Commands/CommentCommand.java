package Commands;

import java.util.ArrayList;
import Context.CalcContext;

public class CommentCommand implements Command {

    @Override
    public void execute(CalcContext context, ArrayList<String> args) {
        context.setComments(args.get(0));
    }
}
