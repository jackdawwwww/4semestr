public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {

        }
        String inputName = "";

        if (args.length == 1)
            inputName = args[0];

        Calc calc = new Calc();
        calc.calculate(inputName);
    }
}
