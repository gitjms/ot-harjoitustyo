package jms.tictactoe.domain;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * Class for handling game winnings, draws, and total played games.
 */

public class Game {

    private int id;
    private Map<String, Pair<Integer,Integer>> scores;
    private ScoreService scoreService;
    
    public Game() {
        this.scores = new HashMap<>();
    }
    
    public Game(int id,ScoreService scoreService) throws FileNotFoundException, IOException, Exception {
        this.id = id;
        this.scoreService = scoreService;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setWinner(String winner) {
        this.scores = this.scoreService.getAllMap();
        int oldScore = this.getScore(winner);
        int oldGames = this.getGames(winner);
        this.scores.put(winner,new Pair(oldScore+1,oldGames+1));
        this.scoreService.setGames(oldGames+1);
    }

    public void setDraw() {
        this.scores = this.scoreService.getAllMap();
        int oldScoreX = this.getScore("X");
        int oldScoreO = this.getScore("O");
        int oldGames = this.getGames("X");
        this.scores.put("X",new Pair(oldScoreX,this.getGames("X")+1));
        this.scores.put("O",new Pair(oldScoreO,this.getGames("O")+1));
        this.scoreService.setGames(oldGames+1);
    }

    public int getScore(String player) {
        return this.scores.get(player).getKey();
    }

    public int getGames(String player) {
        return this.scores.get(player).getValue();
    }

}
