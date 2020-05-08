package jms.tictactoe.ui;

import jms.tictactoe.domain.ScoreData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.paint.Color;

import jms.tictactoe.dao.ScoreDataDao;
import jms.tictactoe.domain.ScoreService;
/**
 *
 * @author jaris
 */
public class GameSquareTest {
    
    private ScoreDataDao databaseDao;
    private Connection connection;
    private Statement statement;
    private ScoreService scoreService;
    private GameSquare instance;
    private Label label;
    private Button button;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING GAMESQUARE CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        ScoreData getData = new ScoreData(this.connection, this.statement);
        this.databaseDao = new ScoreDataDao(getData, this.connection, this.statement);
        this.scoreService = new ScoreService(this.databaseDao);
        this.label = new Label();
        this.instance = new GameSquare(this.label, this.scoreService);
        this.instance.setFinished(false);
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
            if(this.connection!=null)  {
                this.connection.close();
            } 
        } catch(SQLException se) {
        }
    }
    
    @Test
    public void testSetOnMouseClickedOnSquare() {
        System.out.println("testSetOnMouseClickedOnSquare");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0) + " " + Integer.toString(0);
        this.label.setText("something something X");
        this.button = this.instance.createSquare(squares,id);
        this.instance.setOnMouseClicked(this.button, squares,id);
        assertEquals("X", this.button.getText());
    }
    
    @Test
    public void testSetOnMouseClickedUnfinished() {
        System.out.println("testSetOnMouseClickedUnfinished");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0 ) + " " + Integer.toString(0);

        this.instance.setFinished(false);
        this.label.setText("something something O");
        this.button = this.instance.createSquare(squares,id);
        this.instance.setOnMouseClicked(this.button,squares,id);
        assertEquals("O", this.button.getText());
    }
    
    @Test
    public void testSetOnMouseClickedFinished() {
        System.out.println("testSetOnMouseClickedFinished");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0) + " " + Integer.toString(0);
        this.instance.setFinished(true);
        this.label.setText("something something O");
        this.button = this.instance.createSquare(squares,id);
        this.instance.setOnMouseClicked(this.button,squares,id);
        assertEquals(" ", this.button.getText());
    }
    
    @Test
    public void testSetOnMouseClickedEmptySquare() {
        System.out.println("testSetOnMouseClickedEmptySquare");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0) + " " + Integer.toString(0);
        this.label.setText("something something O");
        this.button = new Button();
        this.button.setText("Z");
        this.instance.setOnMouseClicked(this.button,squares,id);
        assertEquals("Z", this.button.getText());
    }

    /**
     * Test of setSquare method, of class GameSquare.
     */
    @Test
    public void testSetSquareChangesButton() {
        System.out.println("testSetSquareChangesButton");
        this.button = new Button("Some text");
        this.instance.setSquare(button, "X", Color.LIGHTPINK, Color.LEMONCHIFFON, "");
        assertEquals("X", this.button.getText());

    }

    /**
     * Test of createSquare method, of class GameSquare.
     */
    @Test
    public void testCreateSquareReturnsButton() {
        System.out.println("testCreateSquareReturnsButton");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0) + " " + Integer.toString(0);
        Button result = this.instance.createSquare(squares, id);
        assertNotNull(result);

    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsNotFinishedWithNoMarks() {
        System.out.println("testGameIsNotFinishedWithNoMarks");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForXDiag() {
        System.out.println("testGameIsFinishedForXDiag");
        String[][] squares = {{"X","O"," "}, {" ","X"," "}, {"O"," ","X"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForODiag() {
        System.out.println("testGameIsFinishedForODiag");
        String[][] squares = {{" ","O","X"}, {" ","X"," "}, {"X"," ","O"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForXVert() {
        System.out.println("testGameIsFinishedForXVert");
        String[][] squares = {{"X","X","X"}, {" ","O"," "}, {"O"," ","X"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForOHoriz() {
        System.out.println("testGameIsFinishedForOHoriz");
        String[][] squares = {{"X","O"," "}, {" ","O","X"}, {" ","O","X"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedWithDrawReturnsTrue() {
        System.out.println("testGameIsFinishedWithDrawReturnsTrue");
        String[][] squares = {{"X","O","X"}, {"O","O","X"}, {"X","X","O"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedWithFinishedSetToTrueReturnsFalse() {
        System.out.println("testGameIsFinishedWithFinishedSetToTrueReturnsFalse");
        this.instance.setFinished(true);
        String[][] squares = {{"X","O","X"}, {"O","O","X"}, {"X","X","O"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

}
