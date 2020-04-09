import Commands.Command;
import Context.CalcContext;
import Exceptions.MyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Calculator {
    private static final MyLog log = new MyLog();

    CalcContext context;
    CommandsFactory commandsFactory;
    boolean isException = false;

    public Calculator() {
        context = new CalcContext();
        commandsFactory = new CommandsFactory();
    }
    public void calculate(String fileName) throws MyException, ClassNotFoundException {

        try {
            Scanner scanner = fileName.equals("") ? new Scanner(System.in) : new Scanner(new File(fileName));
            log.getIfo("Start calculating");

            while (scanner.hasNextLine()) {
                log.getIfo("Start parsing");

                String commandName;
                List<String> args = null;
                List<String> words = Arrays.asList(scanner.nextLine().split(" "));

                commandName = words.get(0);

                if (words.size() > 1)
                    args = words.subList(1, words.size());

                if (args == null) args = Collections.singletonList("");
                log.getIfo("Start '" + commandName );

                Command com = commandsFactory.getCommand(commandName);
                com.execute(context, args);

                log.getIfo("Successful'");
            }
            if (!context.isEmpty()) {
                log.getIfo("Result is: ");
                context.print();
            }
            log.getIfo("Successful calculating");
        } catch (MyException | FileNotFoundException e){
            isException = true;
            log.error(e);
        }
    }

}
