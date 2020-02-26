public class WordStatistic {
    private int counter;
    private double percent;
    private String word;

    WordStatistic(String word, int counter){
        this.word = word;
        this.counter = counter;
        percent = 0;
    }

    int getCount(){
        return counter;
    }

    @Override
    public String toString(){
        return word + ";" + counter + ";" + percent + "%\n";
    }

    void calcPercent(int numOfAll){
        percent = ((double)counter / numOfAll) * 100;
    }
}