package HighScores;
import java.io.Serializable;
import java.util.ArrayList;

public class Highscore implements Serializable {

    private final ArrayList<Score> scoreList;

    public Highscore() {
       scoreList = new ArrayList<Score>();
    }

    public void setScore(int points, String name) {
        int num = scoreList.size();
        final Score score = new Score(points, name, num + 1);
        scoreList.add(score);
    }

    public ArrayList<String> getScores() {
        ArrayList<String> strings = new ArrayList<>();

        for (Score score: scoreList) {
            strings.add(score.toString());
        }

        return strings;
    }

}
