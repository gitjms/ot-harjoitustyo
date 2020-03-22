package jms.tictactoe.domain;

import java.util.HashMap;
import java.util.Map;
//import java.util.Objects;

/**
 * Class for handling game scores.
 */

public class Game {

    private int id;
    private String winner;
    private Map<String, Integer> stats;
    
    public Game() {
        this.stats = new HashMap<>();
    }
    
    public Game(int id) {
        this.id = id;
        this.stats = new HashMap<>();
        this.stats.put("X", 0);
        this.stats.put("O", 0);
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setWinner(String winner) {
        this.winner = winner;
        int score = this.stats.get(winner);
        this.stats.put(winner,score+1);
    }

    public String getWinner() {
        return winner;
    }

    public int getScore(String player) {
        return this.stats.get(player);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Game)) {
//            return false;
//        }
//        Game other = (Game) obj;
//        return id == other.id;
//    }

}
