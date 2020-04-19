package jms.tictactoe.dao;

/**
 * Interface for Data Access Objects.
 * @author jaris
 */
public interface ScoreDao {
        
    int getPoints(String id);
    
    void resetPoints();
    
    int getAmount();
    
    void setAmount(int amount);
    
    int getDraws();
    
    void setDraws(int draws);
    
    void setScore(String player, int points);
}
