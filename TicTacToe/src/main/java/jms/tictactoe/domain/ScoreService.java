package jms.tictactoe.domain;

import java.util.Map;

import javafx.util.Pair;

import jms.tictactoe.dao.ScoreDao;

/**
 * Class for handling application logic.
 * @author jaris
 */
public class ScoreService implements ScoreDao {
    
    private ScoreDao scoreDao;
    
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }
    
    public boolean createScore(String id, int points, int games) {
        Score score = new Score(id, points, games);
        try {   
            this.scoreDao.create(score);
        } catch (Exception ex) {
            System.out.println("ScoreService message: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public Score getScore() {
        return this.scoreDao.getScore();
    }

    public boolean isScore() {
        return this.scoreDao != null;
    }

    @Override
    public Score create(Score score) throws Exception {
        return this.scoreDao.create(score);
    }

    @Override
    public Map<String, Pair<Integer, Integer>> getAllMap() {
        return this.scoreDao.getAllMap();
    }

    @Override
    public void setAllMap(Map<String, Pair<Integer, Integer>> map) {
        this.scoreDao.setAllMap(map);
    }

    @Override
    public void setPoints(String id, int points) {
        this.scoreDao.setPoints(id, points);
    }

    @Override
    public int getPoints(String id) {
        return isScore() == false ? 0 : this.scoreDao.getPoints(id);
    }

    @Override
    public void resetPoints() {
        this.scoreDao.resetPoints();
    }

    @Override
    public void setGames(int games) {
        this.scoreDao.setGames(games);
    }

    @Override
    public int getGames() {
        return isScore() == false ? 0 : this.scoreDao.getGames();
    }

    @Override
    public void setScore(Score score) {
        this.scoreDao.setScore(score);
    }

}
