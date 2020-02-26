public class WordStatistic {
    private int counter;
    private double percent;
    private String word;

    WordStatistic(String word){
        this.word = word;
        counter = 1;
        percent = 0;
    }
    void addOne(){
        counter++;
    }
     int getCount(){
        return counter;
     }

     @Override
     public String toString(){
        return word + ";" + String.valueOf(counter) + ";" + String.valueOf(percent) + "%";
    }

    public void calcPercent(int numOfAll){
        percent = (double)counter / numOfAll;
    }
}
