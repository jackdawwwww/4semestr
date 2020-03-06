import Context.CalcContext;
import Exceptions.ArgExceptions;
import Exceptions.EmptyStackExceptions;
import Exceptions.MyException;
import org.junit.Test;


public class CalcTest {

    @Test
    public void PUSHandPOPTest() throws EmptyStackExceptions {
        CalcContext context = new CalcContext();

        for (double i = 0; i < 100; i++) {
            context.push(i);
        }

        assert (context.size() == 100);

        for (double i = 0; i < 100; i++) {
            context.pop();
        }

        assert (context.isEmpty());
    }

    @Test
    public void DEFINETest() throws ArgExceptions, EmptyStackExceptions {
        CalcContext context = new CalcContext();

        String[] str = {"one", "two", "three", "four", "five", "six"};
        Double[] num = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

        for (int i = 0; i < 6; i++) {
            context.setDefinitions(str[i], num[i].toString());
        }

        for (int i = 0; i < 6; i++) {
            context.push(str[i]);
            context.push(num[i]);
        }
        assert (context.size() == 12);

        for (int i = 0; i < 6; i++) {
            double a = context.pop();
            double b = context.pop();

            assert (a == b);
        }
    }

    @Test
    public void DefTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/DefineTest.txt");
        assert (calculator.context.pop() == 1);
        assert (calculator.context.pop() == 2);
        assert (calculator.context.pop() == 3);
        assert (calculator.context.isEmpty());

        Calculator calculator2 = new Calculator();
        calculator2.calculate("production/NoArgTest.txt");
        assert (calculator2.isException);
    }

    @Test
    public void PlusTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/PlusTest.txt");
        assert(calculator.context.pop() == 15);
        assert(calculator.context.isEmpty());
    }

    @Test
    public void MinusTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/MinusTest.txt");
        assert(calculator.context.pop() == 3);
        assert(calculator.context.isEmpty());
    }

    @Test
    public void MultTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/MultTest.txt");
        assert(calculator.context.pop() == 10);
        assert(calculator.context.isEmpty());
    }

    @Test
    public void DivTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/DivTest.txt");
        assert(calculator.context.pop() == 4);

        calculator.calculate("production/BadDiv.txt");
        assert(calculator.isException);
    }

    @Test
    public void SqrtTest() throws MyException, ClassNotFoundException {
        Calculator calculator = new Calculator();

        calculator.calculate("production/BadSqrt.txt");
        assert(calculator.isException);
    }

}

