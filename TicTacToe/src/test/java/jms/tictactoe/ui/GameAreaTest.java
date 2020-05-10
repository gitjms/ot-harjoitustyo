package jms.tictactoe.ui;

import jms.tictactoe.domain.ScoreData;
import java.sql.SQLException;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import de.saxsys.javafx.test.JfxRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jms.tictactoe.dao.ScoreDataDao;
import jms.tictactoe.domain.ScoreService;

/**
 *
 * @author jaris
 */
@RunWith(JfxRunner.class)
public class GameAreaTest {
    
    private ScoreDataDao databaseDao;
    private Connection connection;
    private Statement statement;
    private ScoreService scoreService;
    private GameArea instance;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING GAMEBOX CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        ScoreData getData = new ScoreData(this.connection, this.statement);
        this.databaseDao = new ScoreDataDao(getData, this.connection, this.statement);
        this.scoreService = new ScoreService(this.databaseDao);
        this.instance = new GameArea(this.scoreService);
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
     * Test of createArea method, of class GameArea.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateAreaReturnsVBox() throws Exception {
        System.out.println("testCreateAreaReturnsVBox");
        VBox result = this.instance.createArea();
        assertNotNull(result);
    }

    /**
     * Test of setGridpane method, of class GameArea.
     */
    @Test
    public void testSetAndGetGridpaneReturnsGridPane() {
        System.out.println("testSetAndGetGridpaneReturnsGridPane");
        this.instance.setGridpane();
        GridPane result = this.instance.getGridpane();
        assertNotNull(result);
    }
    
}
