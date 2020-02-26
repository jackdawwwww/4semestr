import Commands.Command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandsFactory {
    private static CommandsFactory commandsFactory;
    private final Properties properties = new Properties();

    private CommandsFactory() {
        try {
            properties.load(Calc.class.getResourceAsStream("commands.properties"));
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

        try {
            command = (Command) Class.forName(properties.getProperty(commandName)).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
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
