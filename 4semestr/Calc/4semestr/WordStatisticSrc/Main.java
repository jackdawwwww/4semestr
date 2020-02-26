import java.util.List;

public class Main {
    public static <FILE, reading> void main(String[] args) {
        if (args.length <= 1) return;

        String inputFileName = args[0];
        String outputFileName =  args[1];

        Parser parser = new Parser(inputFileName);

        List<String> words = parser.run();

        if (words != null) {
            FileWordsStatistic fileWordsStatistic = new FileWordsStatistic();

            words.forEach(fileWordsStatistic::addWord);

            fileWordsStatistic.printStatistic(outputFileName);
        } else {
            System.exit(1);
        }
    }
}