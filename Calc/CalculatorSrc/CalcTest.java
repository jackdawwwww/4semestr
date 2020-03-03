import Context.CalcContext;
import Exceptions.ArgExceptions;
import Exceptions.EmptyStackExceptions;
import org.junit.Test;


public class CalcTest{

    @Test
    public void PUSHandPOPTest() throws EmptyStackExceptions {
        CalcContext context = new CalcContext();

        for (double i = 0; i < 100; i++) {
            context.push(i);
        }

        assert(context.size() == 100 );

        for (double i = 0; i < 100; i++) {
            context.pop();
        }

        assert(context.isEmpty());
    }

    @Test
    public void DEFINETest() throws ArgExceptions, EmptyStackExceptions {
        CalcContext context = new CalcContext();

        String[] str = {"one", "two", "three", "four", "five", "six"};
        Double[] num = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

        for (int i = 0; i < 5 ; i++) {
            context.setDefinitions(str[i], num[i].toString());
        }

        for (int i = 0; i < 5 ; i++) {
            context.push(str[i]);
            context.push(num[i]);
        }
        assert(context.size() == 10);

        for (int i = 0; i < 5 ; i++) {
            Double a = context.pop();
            Double b = context.pop();

            assert ( a == b);
        }
    }

    @Test
    public void ArithmeticTest() throws EmptyStackExceptions {
        Calculator calculator = new Calculator();


    }

}

