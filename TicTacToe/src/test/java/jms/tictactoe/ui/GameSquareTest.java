package jms.tictactoe.ui;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.paint.Color;

import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.ScoreService;
import jms.tictactoe.domain.Game;
/**
 *
 * @author jaris
 */
@RunWith(JfxRunner.class)
public class GameSquareTest {
    
    private GameSquare instance;
    private FileScoreDao fileScoreDao;
    private ScoreService scoreService;
    private Game game;
    private Label label;
    private Button button;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, Exception {
        this.label = new Label();
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        this.scoreService = new ScoreService(this.fileScoreDao);
        this.game = new Game(1, this.scoreService);
        this.instance = new GameSquare(this.label, this.game, this.scoreService, this.fileScoreDao);
        this.instance.setFinished(false);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSetOnMouseClickedOnSquare() {
        System.out.println("GameSquare TEST: setOnMouseClicked works on empty game");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        String id = Integer.toString(0) + " " + Integer.toString(0);
        this.label.setText("something something X");
        this.button = this.instance.createSquare(squares,id);
        this.instance.setOnMouseClicked(this.button, squares,id);
        assertEquals("X", this.button.getText());
    }
    
    @Test
    public void testSetOnMouseClickedUnfinished() {
        System.out.println("GameSquare TEST: setOnMouseClicked works unfinished");
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
        System.out.println("GameSquare TEST: setOnMouseClicked works finished");
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
        System.out.println("GameSquare TEST: setOnMouseClicked works finished");
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
        System.out.println("GameSquare TEST: setSquare changes Button");
        this.button = new Button("Some text");
        this.instance.setSquare(button, "X", Color.LIGHTPINK, Color.LEMONCHIFFON, "");
        assertEquals("X", this.button.getText());

    }

    /**
     * Test of createSquare method, of class GameSquare.
     */
    @Test
    public void testCreateSquareReturnsButton() {
        System.out.println("GameSquare TEST: createSquare returns Button");
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
        System.out.println("GameSquare TEST: game is not finished with no marks");
        String[][] squares = {{" "," "," "}, {" "," "," "}, {" "," "," "}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForXDiag() {
        System.out.println("GameSquare TEST: game is finished for X diagonally down");
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
        System.out.println("GameSquare TEST: game is finished for O diagonally up");
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
        System.out.println("GameSquare TEST: game is finished for X vertical");
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
        System.out.println("GameSquare TEST: game is finished for O horizontal");
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
        System.out.println("GameSquare TEST: game is finished with draw returns true");
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
        System.out.println("GameSquare TEST: game is finished with finished set to true returns false");
        this.instance.setFinished(true);
        String[][] squares = {{"X","O","X"}, {"O","O","X"}, {"X","X","O"}};
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

}
