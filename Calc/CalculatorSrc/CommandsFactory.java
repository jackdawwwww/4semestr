import Commands.Command;
import Exceptions.InvalidCommandExceptions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandsFactory {
    private static CommandsFactory commandsFactory;
    private final Properties properties = new Properties();

    CommandsFactory() {
        try {
            properties.load(Calculator.class.getResourceAsStream("commands.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CommandsFactory getCommandsFactory() {
        if (commandsFactory == null)
            commandsFactory = new CommandsFactory();

        return commandsFactory;
    }

    public Command getCommand(String commandName) throws ClassNotFoundException {
        Command command = null;
        String  name;
        try {
            if ((name = properties.getProperty(commandName)) == null) throw new InvalidCommandExceptions(commandName);
            command = (Command) Class.forName(name).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvalidCommandExceptions e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return command;
    }
}
