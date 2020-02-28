import Context.CalcContext;
import Exceptions.ArgExceptions;

import java.util.*;


public class Calc {

    CalcContext context;

    public Calc() {
        context = new CalcContext();
    }
    public void calculate(String fileName) throws ArgExceptions {
        Scanner scanner = fileName.equals("") ? new Scanner(System.in) : new Scanner(fileName);

        while (scanner.hasNextLine()) {
            String commandName;
            List<String> args;
            List<String> words = Arrays.asList(scanner.nextLine().split(" "));

            commandName = words.get(0);

            if (commandName == "SQRT" || commandName == "POP" || commandName == "PRINT") {
                if (words.size() > 1) throw new ArgExceptions("Unnecessary args for " + commandName);
                else continue;
            }
            if (commandName == "DEFINE")
                if (words.size() != 3) throw new ArgExceptions("No args for " + commandName);

            else if (words.size() != 2) throw new ArgExceptions("Invalid count of args for " + commandName);

            args = words.subList(1, words.size() - 1);
        }
    }

}
