import java.util.Comparator;
import java.util.TreeSet;


class SetComparator implements Comparator<WordStatistic>{
    public int compare(WordStatistic elem1, WordStatistic elem2) {
        int firstCounter, secondCounter;
        firstCounter = elem1.getCount();
        secondCounter = elem2.getCount();
        if (secondCounter > firstCounter)
            return 1;
        else return 0;
    }
}

class FileWordsStatistic {
    private TreeSet<WordStatistic> statSet;
    private int counter;

    FileWordsStatistic() {
        statSet = new TreeSet<WordStatistic>(new SetComparator());
        counter = 0;
    }
    void addWord(String word) {
        WordStatistic elem = new WordStatistic(word);

        if (!statSet.add(elem)) {
            elem.addOne();
        }
        counter++;
    }
    int getCount(){
        return counter;
    }
    TreeSet<WordStatistic> getStatSet(){
        updateSet();
        return statSet;
    }
    public void updateSet(){
        for (WordStatistic value: statSet){
            value.calcPercent(counter);
        }
    }

}
