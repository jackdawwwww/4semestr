import Exceptions.MyException;


public class Main {

    public static void main(String[] args) throws MyException, ClassNotFoundException {
        String inputName = "";

        if (args.length == 1)
            inputName = args[0];

        Calculator calc = new Calculator();
        calc.calculate(inputName);
    }
}
