package jms.tictactoe.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import jms.tictactoe.dao.ScoreDataDao;

/**
 *
 * @author jaris
 */
public class ScoreServiceTest {
    
    private ScoreDataDao databaseDao;
    private Connection connection;
    private Statement statement;
    private ScoreService instance;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING SCORESERVICE CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        ScoreData getData = new ScoreData(this.connection, this.statement);
        this.databaseDao = new ScoreDataDao(getData, this.connection, this.statement);
        this.instance = new ScoreService(this.databaseDao);
    }
    
    @After
    public void tearDown() throws SQLException {
        try {
            if(!this.statement.isClosed()) {
                this.statement.close();
            } 
        } catch(SQLException se) {
        }
        try {
            if(!this.connection.isClosed()) {
                this.connection.close();
            } 
        } catch(SQLException se) {
        }
    }

    /**
     * Test of resetPoints method, of class ScoreService.
     */
    @Test
    public void testSetScoreAndResetPointsReturnsZeroPoints() {
        System.out.println("testSetScoreAndResetPointsReturnsZeroPoints");
        this.instance.resetPoints();
        this.instance.setScore("X", 10);
        int resultBefore = this.instance.getPoints("X");
        this.instance.resetPoints();
        int resultAfter = this.instance.getPoints("X");
        assertEquals(10, resultBefore);
        assertEquals(0, resultAfter);
    }

    /**
     * Test of getPoints method, of class ScoreService.
     */
    @Test
    public void testSetAndGetPoints() {
        System.out.println("testSetAndGetPoints");
        this.instance.resetPoints();
        this.instance.setScore("X", 10);
        assertEquals(10, this.instance.getPoints("X"));
    }

    /**
     * Test of getAmount method, of class ScoreService.
     */
    @Test
    public void testSetAndGetAmount() {
        System.out.println("testSetAndGetAmount");
        this.instance.resetPoints();
        this.instance.setAmount(100);
        assertEquals(100, this.instance.getAmount());
    }

    /**
     * Test of getDraws method, of class ScoreService.
     */
    @Test
    public void testSetAndGetDraws() {
        System.out.println("testSetAndGetDraws");
        this.instance.resetPoints();
        this.instance.setDraws(100);
        assertEquals(100, this.instance.getDraws());
    }

    /**
     * Test of getDraws method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void closeStatementDirect() throws SQLException {
        System.out.println("testCloseStatementDirect");
        assertFalse(this.statement.isClosed());
        this.statement.close();
        assertTrue(this.statement.isClosed());
    }

    /**
     * Test of closeConnection method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnectionDirect() throws SQLException {
        System.out.println("testCloseConnectionDirect");
        assertFalse(this.connection.isClosed());
        this.connection.close();
        assertTrue(this.connection.isClosed());
    }

    /**
     * Test of getDraws method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void closeStatementIndirect() throws SQLException {
        System.out.println("testCloseStatementIndirect");
        assertFalse(this.statement.isClosed());
        this.instance.closeStatement(this.statement);
        assertTrue(this.statement.isClosed());
    }

    /**
     * Test of closeConnection method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnectionIndirect() throws SQLException {
        System.out.println("testCloseConnectionIndirect");
        assertFalse(this.connection.isClosed());
        this.instance.closeConnection(this.connection);
        assertTrue(this.connection.isClosed());
    }
}
