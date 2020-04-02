package jms.tictactoe.domain;

/**
 * Class for handling game scores.
 * @author jaris
 */
public class Score {
    
    private String id;
    private int points;
    private int games;
    
    public Score(String id, int points, int games) {
        this.id = id;
        this.points = points;
        this.games = games;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getPoints(String id) {
        return points;
    }

    public void setPoints() {
        this.points++;
    }

    public void setPoints(String id, int points) {
        this.points = points;
    }

    public int getGames() {
        return games;
    }

    public void setGames() {
        this.games++;
    }

    public void setGames(int games) {
        this.games = games;
    }

}
