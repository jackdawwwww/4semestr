import java.io.IOException;
import java.util.Properties;

public class MyProperties {
    Properties properties;
    private int _bSize, _eSize, _aSize, _cSize, _wNum, _dNum, _accNum;
    private boolean _useLog;

    MyProperties() throws IOException {
        properties = new Properties();
        properties.load(MyProperties.class.getResourceAsStream("Settings.properties"));

        parseProperties();
    }

    void parseProperties() {
        _bSize = Integer.parseInt(properties.getProperty("bodyStSize"));
        _eSize = Integer.parseInt(properties.getProperty("engStSize"));
        _aSize = Integer.parseInt(properties.getProperty("accStSize"));
        _cSize = Integer.parseInt(properties.getProperty("carStSize"));
        _wNum = Integer.parseInt(properties.getProperty("workersN"));
        _dNum = Integer.parseInt(properties.getProperty("dealersN"));
        _accNum = Integer.parseInt(properties.getProperty("accN"));
        _useLog = Boolean.parseBoolean(properties.getProperty("logSale"));
    }

    int bSize() { return _bSize; }
    int eSize() { return _eSize; }
    int aSize() { return _aSize; }
    int cSize() { return _cSize; }
    int wNum() { return _wNum; }
    int dNum() { return _dNum; }
    int accNum() { return _accNum; }
    boolean useLog() { return _useLog; }

}
