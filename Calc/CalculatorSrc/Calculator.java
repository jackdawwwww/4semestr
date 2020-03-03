import Commands.Command;
import Context.CalcContext;
import Exceptions.MyException;

import java.util.*;


public class Calculator {
    private static final MyLog log = new MyLog();

    CalcContext context;
    CommandsFactory commandsFactory;

    public Calculator() {
        context = new CalcContext();
        commandsFactory = new CommandsFactory();
    }
    public void calculate(String fileName) throws MyException, ClassNotFoundException {

        try {
            Scanner scanner = fileName.equals("") ? new Scanner(System.in) : new Scanner(fileName);
            log.getIfo("Start calculating");

            while (scanner.hasNextLine()) {
                log.getIfo("Start parsing");

                String commandName;
                List<String> args = null;
                List<String> words = Arrays.asList(scanner.nextLine().split(" "));

                commandName = words.get(0);

                if (words.size() > 1)
                    args = words.subList(1, words.size());

                log.getIfo("Start '" + commandName + " " + (args.isEmpty() ? "" : args) + "'");

                Command com = commandsFactory.getCommand(commandName);
                com.execute(context, args);

                log.getIfo("Successful '" + commandName + " " + (args.isEmpty() ? "" : args) + "'");
            }
            if (!context.isEmpty()) {
                log.getIfo("Result is: ");
                context.print();
            }
            log.getIfo("Successful calculating");
        } catch (MyException e){
            log.error(e);
        }
    }

}
