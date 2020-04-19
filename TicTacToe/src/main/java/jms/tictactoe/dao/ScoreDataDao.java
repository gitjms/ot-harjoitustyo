package jms.tictactoe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import jms.tictactoe.domain.ScoreData;

/**
 * Data Access Object for database storaging.
 * @author jaris
 */
public final class ScoreDataDao implements ScoreDao {

    private final ScoreData scoreData;
    
    public ScoreDataDao(ScoreData getData, Connection connection, Statement statement) throws SQLException, Exception {
        this.scoreData = getData;
        this.initiateTables(connection, "SCORES", "GAMES");
        try {
            String scoreQuery = "SELECT player, points FROM scores";
            try (ResultSet resultScore = statement.executeQuery(scoreQuery)) {
                while (resultScore.next()) {
                    this.setScore(resultScore.getString("player").trim(), resultScore.getInt("points"));
                }
            }
            String gameQuery = "SELECT amount, draws FROM games";
            final ResultSet resultGames = statement.executeQuery(gameQuery);
            while (resultGames.next()) {
                this.setAmount(resultGames.getInt("amount"));
                this.setDraws(resultGames.getInt("draws"));
            }
        } catch (SQLException ex) {
        }
    }

    private void initiateTables(Connection connection, String table1, String table2) throws SQLException, Exception {
        if (!checkTable(connection, table1) || !checkTable(connection, table2)) {
            this.scoreData.deleteTable("scores");
            this.scoreData.deleteTable("games");
            this.scoreData.createScoreTable(connection);
            this.scoreData.createGameTable(connection);
        }
    }
    
    private boolean checkTable(Connection connection, String table) throws SQLException, Exception {
        ResultSet resultTable = connection.getMetaData().getTables(null, null, table, new String[] {"TABLE", "VIEW"});
        boolean tableExists = false;
        while (resultTable.next()) {
            String tableName = resultTable.getString("TABLE_NAME");
            if (tableName != null && tableName.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        return tableExists;
    }
    
    @Override
    public void setScore(String player, int points) {
        try {
            this.scoreData.scoreUpdate(player, points);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resetPoints() {
        try {
            this.scoreData.resetUpdate();
            this.scoreData.gameUpdate(0);
            this.scoreData.drawUpdate(0);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getPoints(String id) {
        try {
            return this.scoreData.pointQuery(id);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int getAmount() {
        try {
            return this.scoreData.gameQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void setAmount(int amount) {
        try {
            this.scoreData.gameUpdate(amount);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getDraws() {
        try {
            return this.scoreData.drawQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void setDraws(int draws) {
        try {
            this.scoreData.drawUpdate(draws);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
