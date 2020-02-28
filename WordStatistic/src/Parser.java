import java.io.*;
import java.util.Iterator;
import java.util.TreeSet;

public class Parser {
    private BufferedReader bufferedReader;
    private FileWordsStatistic fileWordsStatistic;

    public void parsingFile() throws IOException {
        StringBuilder word = null;
        boolean isCorrect = true;
        int value;
        while ((value = bufferedReader.read()) != -1) {
            while (isCorrect) {
                if (Character.isLetterOrDigit(value)) {
                    word.append((char) value);
                } else {
                    isCorrect = false;
                }
            }
            fileWordsStatistic.addWord(word.toString());
        }
    }

    public void printStatistic(String fileName){
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            TreeSet<WordStatistic> statSet = fileWordsStatistic.getStatSet();

            Iterator value = statSet.iterator();

            while(value.hasNext()){
                fileWriter.write(value.next().toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Parser(String inputFileName, String outputFileName) {
        fileWordsStatistic  = new FileWordsStatistic();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));

            parsingFile();
            printStatistic(outputFileName);

        } catch (IOException e) {
            System.err.println("Error while reading file:" + e.getLocalizedMessage());
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

}
