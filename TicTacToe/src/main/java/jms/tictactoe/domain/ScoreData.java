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

    public ScoreData(Connection connection, Statement statement) throws SQLException, Exception {
        this.connection = connection;
        this.statement = statement;
    }
    
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
    
    public void deleteTable(String table) throws SQLException {
        this.connection.prepareStatement("DROP TABLE " + table + " IF EXISTS;").executeUpdate();
    }
    
    public int pointQuery(String id) throws SQLException {
        int points = 0;
        ResultSet result = this.statement.executeQuery("SELECT points FROM scores WHERE player = '" + id + "';");
        while (result.next()) {
            points = result.getInt("points");
        }
        return points;
    }
    
    public int gameQuery() throws SQLException {
        int amount = 0;
        ResultSet result = this.statement.executeQuery("SELECT amount FROM games;");
        while (result.next()) {
            amount = result.getInt("amount");
        }
        return amount;
    }
    
    public int drawQuery() throws SQLException {
        int draws = 0;
        ResultSet result = this.statement.executeQuery("SELECT draws FROM games;");
        while (result.next()) {
            draws = result.getInt("draws");
        }
        return draws;
    }

    public void scoreUpdate(String player, int points) throws SQLException {
        this.statement.executeUpdate("UPDATE scores SET points = " + points + " WHERE player = '" + player + "';");
    }

    public void resetUpdate() throws SQLException {
        this.statement.executeUpdate("UPDATE scores SET points = 0; UPDATE games SET amount = 0; UPDATE games SET draws = 0");
    }

    public void gameUpdate(int games) throws SQLException {
        this.statement.executeUpdate("UPDATE games SET amount = " + games + ";");
    }

    public void drawUpdate(int draws) throws SQLException {
        this.statement.executeUpdate("UPDATE games SET draws = " + draws + ";");
    }
    
    public void closeStatement(Statement statement) throws SQLException {
        if (!statement.isClosed()) {
            statement.close();
        }
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
