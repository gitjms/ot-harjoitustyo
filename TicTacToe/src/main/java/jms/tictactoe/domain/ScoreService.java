package jms.tictactoe.domain;

import jms.tictactoe.dao.ScoreDao;

/**
 * Class for handling application logic.
 * @author jaris
 */
public class ScoreService implements ScoreDao {
    
    private final ScoreDao scoreDao;
    
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }
    
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
}
