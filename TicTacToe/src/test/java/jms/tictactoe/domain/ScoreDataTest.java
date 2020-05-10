package jms.tictactoe.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaris
 */
public class ScoreDataTest {
    
    private final String testScoreData = "jdbc:h2:~/test";
    private final String dbUser = "sa"; 
    private final String dbPass = "";
    
    public ScoreDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING SCOREDATA CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, SQLException, Exception {
        System.out.print("    - ");
    }
    
    @After
    public void tearDown() throws SQLException, Exception {
    }
    
    /**
     * Test of createScoreTable method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateScoreTable() throws Exception {
        System.out.println("testCreateScoreTable");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.createScoreTable(connection);
        assertNotNull(instance);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }
    
    /**
     * Test of createGameTable method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateGameTable() throws Exception {
        System.out.println("testCreateGameTable");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.createGameTable(connection);
        assertNotNull(instance);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }
    
    /**
     * Test of scoreUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testScoreUpdate() throws Exception {
        System.out.println("testScoreUpdate");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.resetUpdate();
        int pointsBefore = instance.pointQuery("X");
        instance.scoreUpdate("X", 100);
        int pointsAfter = instance.pointQuery("X");
        assertEquals(100, pointsAfter - pointsBefore);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }
    
    /**
     * Test of scoreUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrongScoreUpdate() throws Exception {
        System.out.println("testWrongScoreUpdate");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.resetUpdate();
        int pointsBefore = instance.pointQuery("X");
        instance.scoreUpdate("Y", 100);
        int pointsAfter = instance.pointQuery("X");
        assertEquals(0, pointsAfter - pointsBefore);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }

    /**
     * Test of resetUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testResetUpdate() throws Exception {
        System.out.println("testResetUpdate");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.resetUpdate();
        instance.scoreUpdate("X", 100);
        int pointsBefore = instance.pointQuery("X");
        instance.resetUpdate();
        int pointsAfter = instance.pointQuery("X");
        assertEquals(100, pointsBefore - pointsAfter);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }

    /**
     * Test of gameUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGameUpdate() throws Exception {
        System.out.println("testGameUpdate");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.resetUpdate();
        int gamesBefore = instance.gameQuery();
        instance.gameUpdate(100);
        int gamesAfter = instance.gameQuery();
        assertEquals(100, gamesAfter - gamesBefore);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }

    /**
     * Test of drawUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testDrawUpdate() throws Exception {
        System.out.println("testDrawUpdate");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        instance.resetUpdate();
        int drawsBefore = instance.drawQuery();
        instance.drawUpdate(100);
        int drawsAfter = instance.drawQuery();
        assertEquals(100, drawsAfter - drawsBefore);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }
    
    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloseStatement() throws Exception {
        System.out.println("testCloseStatement");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        statement = connection.createStatement();
        boolean isStatementClosed = statement.isClosed();
        assertFalse(isStatementClosed);
        instance.closeStatement(statement);
        isStatementClosed = statement.isClosed();
        assertTrue(isStatementClosed);
    }

    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloseClosedStatement() throws Exception {
        System.out.println("testCloseClosedStatement");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        statement = connection.createStatement();
        boolean isStatementClosed = statement.isClosed();
        assertFalse(isStatementClosed);
        instance.closeStatement(statement);
        isStatementClosed = statement.isClosed();
        assertTrue(isStatementClosed);
        instance.closeStatement(statement);
        isStatementClosed = statement.isClosed();
        assertTrue(isStatementClosed);
    }

    /**
     * Test of closeConnection method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("testCloseConnection");
        Connection connection = DriverManager.getConnection(this.testScoreData, this.dbUser, this.dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        boolean isConnectionClosed = connection.isClosed();
        assertFalse(isConnectionClosed);
        instance.closeConnection(connection);
        isConnectionClosed = connection.isClosed();
        assertTrue(isConnectionClosed);
    }

    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloseClosedConnection() throws Exception {
        System.out.println("testCloseClosedConnection");
        Connection connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        boolean isConnectionClosed = connection.isClosed();
        assertFalse(isConnectionClosed);
        instance.closeConnection(connection);
        isConnectionClosed = connection.isClosed();
        assertTrue(isConnectionClosed);
        instance.closeConnection(connection);
        isConnectionClosed = connection.isClosed();
        assertTrue(isConnectionClosed);
    }

    /**
     * Test of closeConnection method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteTable() throws Exception {
        System.out.println("testDeleteTable");
        Connection connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        Statement statement = connection.createStatement();
        ScoreData instance = new ScoreData(connection, statement);
        ResultSet resultTable = connection.getMetaData().getTables(null, null, "SCORES", new String[] {"TABLE","VIEW"});
        boolean tableExists = false;
        while (resultTable.next()) {
            String tableName = resultTable.getString("TABLE_NAME");
            if (tableName != null && tableName.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        assertTrue(tableExists);
        instance.deleteTable("SCORES");
        resultTable = connection.getMetaData().getTables(null, null, "SCORES", new String[] {"TABLE","VIEW"});
        tableExists = false;
        while (resultTable.next()) {
            String tableName = resultTable.getString("TABLE_NAME");
            if (tableName != null && tableName.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        instance.createScoreTable(connection);
        assertFalse(tableExists);
        instance.closeStatement(statement);
        instance.closeConnection(connection);
    }
    
}
