package HighScores;

import java.io.Serializable;

public class Score implements Serializable {
    private int points;
    private String name;
    private int gameNum;

    public Score(int points, String name, int gameNum) {
        this.points = points;
        this.name = name;
        this.gameNum = gameNum;
    }

    public int getPoints() { return points; }
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString(){
        return "№" + gameNum + ". " + name + ": " + points + " points";
    }

}
