package jms.tictactoe.domain;

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

import jms.tictactoe.dao.FileScoreDao;
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
    private boolean finished;
    
    public GameSquareTest() {
    }
    
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
        this.game = new Game(1,this.scoreService);
        this.instance = new GameSquare(this.label,this.game,this.scoreService,this.fileScoreDao);
        this.instance.setFinished(false);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createSquare method, of class GameSquare.
     */
    @Test
    public void testCreateSquareReturnsButton() {
        System.out.println("GameSquare TEST SUCCESS: createSquare returns Button");
        String[][] squares = {{" "," "," "},{" "," "," "},{" "," "," "}};
        String id = Integer.toString(0)+" "+Integer.toString(0);
        Button result = this.instance.createSquare(squares, id);
        assertNotNull(result);

    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsNotFinishedWithNoMarks() {
        System.out.println("GameSquare TEST SUCCESS: game is not finished with no marks");
        String[][] squares = {{" "," "," "},{" "," "," "},{" "," "," "}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForXDiag1() {
        System.out.println("GameSquare TEST SUCCESS: game is finished for X diagonally down");
        String[][] squares = {{"X","O"," "},{" ","X"," "},{"O"," ","X"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForODiag2() {
        System.out.println("GameSquare TEST SUCCESS: game is finished for O diagonally up");
        String[][] squares = {{" ","O","X"},{" ","X"," "},{"X"," ","O"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForXVert() {
        System.out.println("GameSquare TEST SUCCESS: game is finished for X vertical");
        String[][] squares = {{"X","X","X"},{" ","O"," "},{"O"," ","X"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedForOHoriz() {
        System.out.println("GameSquare TEST SUCCESS: game is finished for O horizontal");
        String[][] squares = {{"X","O"," "},{" ","O","X"},{" ","O","X"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedWithDrawReturnsTrue() {
        System.out.println("GameSquare TEST SUCCESS: game is finished with draw returns true");
        String[][] squares = {{"X","O","X"},{"O","O","X"},{"X","X","O"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(true, result);
    }

    /**
     * Test of isFinished method, of class GameSquare.
     */
    @Test
    public void testGameIsFinishedWithFinishedSetToTrueReturnsFalse() {
        System.out.println("GameSquare TEST SUCCESS: game is finished with finished set to true returns false");
        this.instance.setFinished(true);
        String[][] squares = {{"X","O","X"},{"O","O","X"},{"X","X","O"}};
        boolean result = this.instance.isFinished(squares);
        assertEquals(false, result);
    }

    /**
     * Test of setTextlabel method, of class GameSquare.
     */
    @Test
    public void testSetTextlabelNotNull() {
        System.out.println("GameSquare TEST SUCCESS: set and get textlabel is not null");
        Label result = this.instance.getTextlabel();
        assertNotNull(result);
    }

}
