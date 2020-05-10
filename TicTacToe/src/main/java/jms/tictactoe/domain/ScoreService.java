package jms.tictactoe.domain;

import java.sql.Connection;
import java.sql.Statement;

import jms.tictactoe.dao.ScoreDao;

/**
 * Class for handling application logic.
 * @author jaris
 */
public class ScoreService implements ScoreDao {
    
    private final ScoreDao scoreDao;
    
    /**
     * Constructor for the class ScoreService.
     * @param scoreDao instance of DAO interface
     */
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }
    
    /**
     * Method for creating score object.
     * @param id whose score to create (player)
     * @param points player points for the score
     */
    public void createScore(String id, int points) { 
        this.scoreDao.setScore(id, points);
    }

    @Override
    public void setScore(String player, int points) {
        this.scoreDao.setScore(player, points);
    }

    @Override
    public void resetPoints() {
        this.scoreDao.resetPoints();
    }

    @Override
    public int getPoints(String id) {
        return this.scoreDao.getPoints(id);
    }

    @Override
    public int getAmount() {
        return this.scoreDao.getAmount();
    }

    @Override
    public void setAmount(int amount) {
        this.scoreDao.setAmount(amount);
    }

    @Override
    public int getDraws() {
        return this.scoreDao.getDraws();
    }

    @Override
    public void setDraws(int draws) {
        this.scoreDao.setDraws(draws);
    }
    
    /**
     * Method for closing the database statement.
     * @param statement command for database controlling to close
     */
    @Override
    public void closeStatement(Statement statement) {
        this.scoreDao.closeStatement(statement);
    }
    
    /**
     * Method for closing the database connection.
     * @param connection database to close
     */
    @Override
    public void closeConnection(Connection connection) {
        this.scoreDao.closeConnection(connection);
    }
}
