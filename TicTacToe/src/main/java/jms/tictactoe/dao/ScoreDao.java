package jms.tictactoe.dao;

/**
 * Interface for Data Access Objects.
 * @author jaris
 */
public interface ScoreDao {
    /**
     * Method for getting points.
     * @param id for whose points to get
     * @return points or default 0
     */
    int getPoints(String id);
    
    /**
     * Method for resetting all points and game data.
     */
    void resetPoints();
    
    /**
     * Method for getting amount of played games.
     * @return amount of played games
     */
    int getAmount();
    
    /**
     * Method for setting amount of played games.
     * @param amount of played games
     */
    void setAmount(int amount);
    
    /**
     * Method for getting amount of draws.
     * @return amount of draws
     */
    int getDraws();
    
    /**
     * Method for setting amount of draws.
     * @param draws amount of draws to set
     */
    void setDraws(int draws);
    
    /**
     * Method for setting score, i.e. points for certain player.
     * @param player whose score to set
     * @param points to set for the player
     */
    void setScore(String player, int points);
}
