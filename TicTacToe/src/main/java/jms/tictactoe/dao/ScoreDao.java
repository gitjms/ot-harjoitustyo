package jms.tictactoe.dao;

import jms.tictactoe.domain.Score;
import java.util.Map;
import javafx.util.Pair;

/**
 * Interface for Data Access Objects.
 * @author jaris
 */
public interface ScoreDao {
    
    Score create(Score score) throws Exception;
        
    Map<String, Pair<Integer,Integer>> getAllMap();
    
    void setAllMap(Map<String, Pair<Integer,Integer>> map);
    
    int getPoints(String id);
    
    void resetPoints();
    
    void setPoints(String id,int points);
    
    int getGames();
    
    void setGames(int games);
    
    Score getScore();
    
    void setScore(Score score);
}
