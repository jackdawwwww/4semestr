
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Parser {
    private String inputFileName;

    Parser(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    private List<String> parseFile(BufferedReader bufferedReader) throws IOException {
        List<String> allWords = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        int value;

        do {
            value = bufferedReader.read();

            if (value != -1 && Character.isLetterOrDigit(value)) {
                word.append((char) value);
            } else if (word.length() != 0) {
                allWords.add(word.toString());
                word = new StringBuilder();
            }
        } while (value != -1);

        return allWords;
    }

    List<String> run() {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));

            return parseFile(bufferedReader);
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

        return null;
    }
}