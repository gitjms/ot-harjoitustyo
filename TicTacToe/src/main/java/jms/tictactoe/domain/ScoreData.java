package jms.tictactoe.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class for handling database storaging.
 * @author jaris
 */
public class ScoreData {

    private final Connection connection;
    private final Statement statement;

    /**
     * Constructor for the class ScoreData.
     * @param connection database
     * @param statement command for database controlling
     * @throws SQLException throws SQL exception
     * @throws Exception throws exception
     */
    public ScoreData(Connection connection, Statement statement) throws SQLException, Exception {
        this.connection = connection;
        this.statement = statement;
    }
    
    /**
     * Method for creating dataabase table for scores.
     * @param connection database
     * @throws Exception throws exception
     */
    public void createScoreTable(Connection connection) throws Exception {
        connection.prepareStatement("DROP TABLE scores IF EXISTS;").executeUpdate();
        connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS scores (\n"
            + "    player VARCHAR(1),\n"
            + "    points INTEGER);").executeUpdate();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO scores (player, points)\n"
            + "VALUES (?,?),(?,?);",
            Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "X");
        preparedStatement.setInt(2, 0);
        preparedStatement.setString(3, "O");
        preparedStatement.setInt(4, 0);
        preparedStatement.executeUpdate();
        preparedStatement.closeOnCompletion();
    }
    
    /**
     * Method for creating dataabase table for games.
     * @param connection database
     * @throws Exception throws exception
     */
    public void createGameTable(Connection connection) throws Exception {
        connection.prepareStatement("DROP TABLE games IF EXISTS;").executeUpdate();
        connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS games (\n"
            + "    amount INTEGER,\n"
            + "    draws INTEGER);").executeUpdate();
        PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INTO games (amount, draws)\n"
            + "VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, 0);
        preparedStatement.executeUpdate();
        preparedStatement.closeOnCompletion();
    }
    
    /**
     * Method for removing database table.
     * @param table to remove
     * @throws SQLException throws SQL exception
     */
    public void deleteTable(String table) throws SQLException {
        this.connection.prepareStatement("DROP TABLE " + table + " IF EXISTS;").executeUpdate();
    }
    
    /**
     * Method for getting player points from database table.
     * @param id player id
     * @return points player points
     * @throws SQLException throws SQL exception
     */
    public int pointQuery(String id) throws SQLException {
        int points = 0;
        ResultSet result = this.statement.executeQuery("SELECT points FROM scores WHERE player = '" + id + "';");
        while (result.next()) {
            points = result.getInt("points");
        }
        return points;
    }
    
    /**
     * Method for getting amount of games from database table.
     * @return amount amount of of games
     * @throws SQLException throws SQL exception
     */
    public int gameQuery() throws SQLException {
        int amount = 0;
        ResultSet result = this.statement.executeQuery("SELECT amount FROM games;");
        while (result.next()) {
            amount = result.getInt("amount");
        }
        return amount;
    }
    
    /**
     * Method for getting amount of draws from database table.
     * @return draws amount of draws
     * @throws SQLException throws SQL exception
     */
    public int drawQuery() throws SQLException {
        int draws = 0;
        ResultSet result = this.statement.executeQuery("SELECT draws FROM games;");
        while (result.next()) {
            draws = result.getInt("draws");
        }
        return draws;
    }

    /**
     * Method for updating database table for scores.
     * @param player whose scores to update
     * @param points player points to update
     * @throws SQLException throws SQL exception
     */
    public void scoreUpdate(String player, int points) throws SQLException {
        this.statement.executeUpdate("UPDATE scores SET points = " + points + " WHERE player = '" + player + "';");
    }

    /**
     * Method for resetting database table for scores (set points and games to zero).
     * @throws SQLException throws SQL exception
     */
    public void resetUpdate() throws SQLException {
        this.statement.executeUpdate("UPDATE scores SET points = 0; UPDATE games SET amount = 0; UPDATE games SET draws = 0");
    }

    /**
     * Method for updating database table for games.
     * @param games amount of games to update
     * @throws SQLException throws SQL exception
     */
    public void gameUpdate(int games) throws SQLException {
        this.statement.executeUpdate("UPDATE games SET amount = " + games + ";");
    }

    /**
     * Method for updating database table for draws.
     * @param draws amount of draws to update
     * @throws SQLException throws SQL exception
     */
    public void drawUpdate(int draws) throws SQLException {
        this.statement.executeUpdate("UPDATE games SET draws = " + draws + ";");
    }
    
    /**
     * Method for closing database statement.
     * @param statement command for database controlling
     * @throws SQLException throws SQL exception
     */
    public void closeStatement(Statement statement) throws SQLException {
        if (!statement.isClosed()) {
            statement.close();
        }
    }
    
    /**
     * Method for closing database connection.
     * @param connection database
     * @throws SQLException throws SQL exception
     */
    public void closeConnection(Connection connection) throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
