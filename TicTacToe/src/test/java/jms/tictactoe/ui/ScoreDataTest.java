package jms.tictactoe.ui;

import jms.tictactoe.domain.ScoreData;
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
    
    private Connection connection;
    private Statement statement;
    private ScoreData instance;
    
    public ScoreDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING SCOREDATA CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, SQLException, Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        this.instance = new ScoreData(this.connection, this.statement);
    }
    
    @After
    public void tearDown() throws SQLException, Exception {
        this.instance.deleteTable("SCORES");
        this.instance.deleteTable("GAMES");
        this.instance.createScoreTable(this.connection);
        this.instance.createGameTable(this.connection);
        try {
            if(!this.statement.isClosed()) {
                this.statement.close();
            } 
        } catch(SQLException se) {
        }
        try {
            if(this.connection!=null) {
                this.connection.close();
            } 
        } catch(SQLException se) {
        }
    }
    
    /**
     * Test of createScoreTable method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateScoreTable() throws Exception {
        System.out.println("testCreateScoreTable");
        this.instance.createScoreTable(this.connection);
        assertNotNull(this.instance);
    }
    
    /**
     * Test of createGameTable method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateGameTable() throws Exception {
        System.out.println("testCreateGameTable");
        this.instance.createGameTable(this.connection);
        assertNotNull(this.instance);
    }
    
    /**
     * Test of scoreUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testScoreUpdate() throws Exception {
        System.out.println("testScoreUpdate");
        this.instance.resetUpdate();
        int pointsBefore = this.instance.pointQuery("X");
        this.instance.scoreUpdate("X", 100);
        int pointsAfter = this.instance.pointQuery("X");
        assertEquals(100, pointsAfter - pointsBefore);
    }
    
    /**
     * Test of scoreUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testWrongScoreUpdate() throws Exception {
        System.out.println("testWrongScoreUpdate");
        this.instance.resetUpdate();
        int pointsBefore = this.instance.pointQuery("X");
        this.instance.scoreUpdate("Y", 100);
        int pointsAfter = this.instance.pointQuery("X");
        assertEquals(0, pointsAfter - pointsBefore);
    }

    /**
     * Test of resetUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testResetUpdate() throws Exception {
        System.out.println("testResetUpdate");
        this.instance.resetUpdate();
        this.instance.scoreUpdate("X", 100);
        int pointsBefore = this.instance.pointQuery("X");
        this.instance.resetUpdate();
        int pointsAfter = this.instance.pointQuery("X");
        assertEquals(100, pointsBefore - pointsAfter);
    }

    /**
     * Test of gameUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGameUpdate() throws Exception {
        System.out.println("testGameUpdate");
        this.instance.resetUpdate();
        int gamesBefore = this.instance.gameQuery();
        this.instance.gameUpdate(100);
        int gamesAfter = this.instance.gameQuery();
        assertEquals(100, gamesAfter - gamesBefore);
    }

    /**
     * Test of drawUpdate method, of class ScoreData.
     * @throws java.lang.Exception
     */
    @Test
    public void testDrawUpdate() throws Exception {
        System.out.println("testDrawUpdate");
        this.instance.resetUpdate();
        int drawsBefore = this.instance.drawQuery();
        this.instance.drawUpdate(100);
        int drawsAfter = this.instance.drawQuery();
        assertEquals(100, drawsAfter - drawsBefore);
    }
    
    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test
    public void testCloseStatement() throws  SQLException, IOException {
        System.out.println("testCloseStatement");
        this.statement = this.connection.createStatement();
        boolean isStatementClosed = this.statement.isClosed();
        assertFalse(isStatementClosed);
        this.instance.closeStatement(this.statement);
        isStatementClosed = this.statement.isClosed();
        assertTrue(isStatementClosed);
        this.statement = this.connection.createStatement();
    }

    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseClosedStatement() throws SQLException {
        System.out.println("testCloseClosedStatement");
        this.statement = this.connection.createStatement();
        boolean isStatementClosed = this.statement.isClosed();
        assertFalse(isStatementClosed);
        this.instance.closeStatement(this.statement);
        isStatementClosed = this.statement.isClosed();
        assertTrue(isStatementClosed);
        this.instance.closeStatement(this.statement);
        isStatementClosed = this.statement.isClosed();
        assertTrue(isStatementClosed);
        this.statement = this.connection.createStatement();
    }

    /**
     * Test of closeConnection method, of class ScoreData.
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testCloseConnection() throws SQLException, FileNotFoundException, IOException {
        System.out.println("testCloseConnection");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        boolean isConnectionClosed = this.connection.isClosed();
        assertFalse(isConnectionClosed);
        this.instance.closeConnection(this.connection);
        isConnectionClosed = this.connection.isClosed();
        assertTrue(isConnectionClosed);
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
    }

    /**
     * Test of closeStatement method, of class ScoreData.
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testCloseClosedConnection() throws SQLException, FileNotFoundException, IOException {
        System.out.println("testCloseClosedConnection");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        boolean isConnectionClosed = this.connection.isClosed();
        assertFalse(isConnectionClosed);
        this.instance.closeConnection(this.connection);
        isConnectionClosed = this.connection.isClosed();
        assertTrue(isConnectionClosed);
        this.instance.closeConnection(this.connection);
        isConnectionClosed = this.connection.isClosed();
        assertTrue(isConnectionClosed);
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
    }

    /**
     * Test of closeConnection method, of class ScoreData.
     * @throws java.sql.SQLException
     */
    @Test
    public void testDeleteTable() throws SQLException, Exception {
        System.out.println("testDeleteTable");
        ResultSet resultTable = this.connection.getMetaData().getTables(null, null, "SCORES", new String[] {"TABLE","VIEW"});
        boolean tableExists = false;
        while (resultTable.next()) {
            String tableName = resultTable.getString("TABLE_NAME");
            if (tableName != null && tableName.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        assertTrue(tableExists);
        this.instance.deleteTable("SCORES");
        resultTable = this.connection.getMetaData().getTables(null, null, "SCORES", new String[] {"TABLE","VIEW"});
        tableExists = false;
        while (resultTable.next()) {
            String tableName = resultTable.getString("TABLE_NAME");
            if (tableName != null && tableName.equals(tableName)) {
                tableExists = true;
                break;
            }
        }
        this.instance.createScoreTable(this.connection);
        assertFalse(tableExists);
    }
    
}
