package jms.tictactoe.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jms.tictactoe.domain.ScoreData;

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
public class ScoreDataDaoTest {
    
    private ScoreDataDao instance;
    private ScoreData getData;
    private Connection connection;
    private Statement statement;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("\nTESTING SCOREDATADAO CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("");
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        this.getData = new ScoreData(this.connection, this.statement);
        this.instance = new ScoreDataDao(this.getData, this.connection, this.statement);
    }
    
    @After
    public void tearDown() throws SQLException, FileNotFoundException, IOException {
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
     * Test of ScoreDataDao, of class ScoreDataDao.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test
    public void testScoreDataDaoWithScoreData() throws  SQLException, IOException, Exception {
        System.out.println("testScoreDataDaoWithScoreData");
        ScoreDataDao scoreDataDao = new ScoreDataDao(this.getData, this.connection, this.statement);
        assertNotNull(String.valueOf(scoreDataDao));
    }

    /**
     * Test of ScoreDataDao, of class ScoreDataDao.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test(expected=NullPointerException.class)
    public void testScoreDataDaoFailsWithNullScoreData() throws  SQLException, IOException, Exception {
        System.out.println("testScoreDataDaoFailsWithNullScoreData");
        ScoreDataDao scoreDataDao = new ScoreDataDao(null, this.connection, this.statement);
        fail(String.valueOf(scoreDataDao));
    }

    /**
     * Test of ScoreDataDao, of class ScoreDataDao.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test(expected=NullPointerException.class)
    public void testScoreDataDaoFailsWithNullConnection() throws  SQLException, IOException, Exception {
        System.out.println("testScoreDataDaoFailsWithNullConnection");
        ScoreDataDao scoreDataDao = new ScoreDataDao(this.getData, null, this.statement);
        fail(String.valueOf(scoreDataDao));
    }

    /**
     * Test of ScoreDataDao, of class ScoreDataDao.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test(expected=NullPointerException.class)
    public void testScoreDataDaoFailsWithNullStatement() throws  SQLException, IOException, Exception {
        System.out.println("testScoreDataDaoFailsWithNullStatement");
        ScoreDataDao scoreDataDao = new ScoreDataDao(this.getData, this.connection, null);
        fail(String.valueOf(scoreDataDao));
    }
    
    /**
     * Test of scoreDataDao, of class ScoreDataDao.
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @Test
    public void testscoreDataDaoFailsNot() throws  SQLException, IOException {
        System.out.println("testscoreDataDaoFailsNot");
        assertNotNull(this.instance);
    }

    /**
     * Test of setScore method, of class ScoreDataDao.
     */
    @Test
    public void testSetScoreWithEmptyPlayer() {
        System.out.println("testSetScoreWithEmptyPlayer");
        this.instance.setScore("", 100);
        int result = this.instance.getPoints("");
        assertEquals(0, result);
    }

    /**
     * Test of setScore method, of class ScoreDataDao.
     */
    @Test
    public void testSetScoreGetWithWrongPlayer() {
        System.out.println("testSetScoreGetWithWrongPlayer");
        this.instance.setScore("X", 100);
        int result = this.instance.getPoints("Y");
        assertEquals(0, result);
    }

    /**
     * Test of resetPoints method, of class ScoreDataDao.
     */
    @Test
    public void testResetAndGetPoints() {
        System.out.println("testResetAndGetPoints");
        this.instance.setScore("X", 10);
        int resultBefore = this.instance.getPoints("X");
        this.instance.resetPoints();
        int resultAfter = this.instance.getPoints("X");
        assertEquals(10, resultBefore - resultAfter);
    }

    /**
     * Test of getAmount method, of class ScoreDataDao.
     */
    @Test
    public void testSetAndGetAmount() {
        System.out.println("testSetAndGetAmount");
        this.instance.resetPoints();
        this.instance.setAmount(100);
        int resultAfter = this.instance.getAmount();
        assertEquals(100, resultAfter);
    }

    /**
     * Test of getDraws method, of class ScoreDataDao.
     */
    @Test
    public void testSetAndGetDraws() {
        System.out.println("testSetAndGetDraws");
        this.instance.resetPoints();
        this.instance.setDraws(100);
        int resultAfter = this.instance.getDraws();
        assertEquals(100, resultAfter);
    }

    /**
     * Test of getDraws method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void closeStatement() throws SQLException {
        System.out.println("testCloseStatement");
        assertFalse(this.statement.isClosed());
        this.instance.closeStatement(this.statement);
        assertTrue(this.statement.isClosed());
    }

    /**
     * Test of closeConnection method, of class ScoreService.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnection() throws SQLException {
        System.out.println("testCloseConnection");
        assertFalse(this.connection.isClosed());
        this.instance.closeConnection(this.connection);
        assertTrue(this.connection.isClosed());
    }
    
}
