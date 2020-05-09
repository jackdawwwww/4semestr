package Factory.Production;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MyLogger {
    private static Logger LOGGER;

    static {
        try (FileInputStream ins = new FileInputStream("myLog")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(MyLogger.class.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void error(Exception e) {
        LOGGER.log(Level.INFO, e.getMessage());
    }

    public void getIfo(String s) {
        LOGGER.log(Level.INFO, s);
    }
}
