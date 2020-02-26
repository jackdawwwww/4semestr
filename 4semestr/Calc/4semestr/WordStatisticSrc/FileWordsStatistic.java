import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class FileWordsStatistic {
    private HashMap<String, WordStatistic> wordCounts;
    private int counter;

    FileWordsStatistic() {
        wordCounts = new HashMap<>();
        counter = 0;
    }
    void addWord(String word) {
        WordStatistic oldStat = wordCounts.get(word);
        int newCount = 1;

        if ( oldStat != null ) {
            newCount = oldStat.getCount() + 1;
        }

        wordCounts.put(word, new WordStatistic(word, newCount));

        counter++;
    }

    private List<WordStatistic> getSortedStatList() {
        List<WordStatistic> statList = new ArrayList<>();

        wordCounts.forEach((key, value) -> statList.add(value));

        statList.sort((e1, e2) -> Integer.compare(e2.getCount(), e1.getCount()));
        statList.forEach(e -> e.calcPercent(counter));

        return statList;
    }

    void printStatistic(String fileName) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(new File(fileName));
            List<WordStatistic> statList = getSortedStatList();

            for (WordStatistic value: statList){
                fileWriter.write(value.toString());
            }
        } catch (IOException e) {
            System.err.println("Error while writing file:" + e.getLocalizedMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("Error while closing file:" + e.getLocalizedMessage());
                }
            }
        }
    }
}