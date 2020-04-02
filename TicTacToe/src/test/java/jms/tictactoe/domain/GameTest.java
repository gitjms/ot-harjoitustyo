package jms.tictactoe.domain;

import java.io.FileInputStream;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import jms.tictactoe.dao.FileScoreDao;

/**
 *
 * @author jaris
 */
public class GameTest {
    
    private Game instance;
    private Game instance2;
    private FileScoreDao fileScoreDao;
    private ScoreService scoreService;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        this.scoreService = new ScoreService(this.fileScoreDao);
        
        this.instance = new Game(1, this.scoreService);
        this.instance2 = new Game();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Game.
     */
    @Test
    public void testSetAndGetIdReturnsId() {
        System.out.println("Game TEST: setId and getId returns Id");
        this.instance.setId(5);
        int result = this.instance.getId();
        assertEquals(5, result);
        this.instance2.setId(5);
        int result2 = this.instance2.getId();
        assertEquals(5, result2);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test(expected=NullPointerException.class)
    public void testGetNullScore() {
        System.out.println("Game TEST: get null score returns fail");
        int result = this.instance.getScore("");
        fail(String.valueOf(result));
        int result2 = this.instance2.getScore("");
        fail(String.valueOf(result2));
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testAddScoreAndGetScoreReturnsScore() {
        System.out.println("Game TEST: add score and get score returns score");
        this.instance.setWinner("X");
        int resultBefore = this.instance.getScore("X");
        this.instance.setWinner("X");
        int resultAfter = this.instance.getScore("X");
        int result = resultAfter-resultBefore;
        assertEquals(1, result);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testSetDrawAddsNoScore() {
        System.out.println("Game TEST: setDraw adds no score");
        this.instance.setWinner("X");
        int resultBefore = this.instance.getScore("X");
        this.instance.setDraw();
        int resultAfter = this.instance.getScore("X");
        int result = resultAfter-resultBefore;
        assertEquals(0, result);
    }

    /**
     * Test of setDraw method, of class Game.
     */
    @Test
    public void testSetDrawAddsGames() {
        System.out.println("Game TEST: setDraw adds games");
        this.instance.setWinner("X");
        int resultBefore = this.instance.getGames("X");
        this.instance.setDraw();
        int resultAfter = this.instance.getGames("X");
        int result = resultAfter-resultBefore;
        assertEquals(1, result);
    }
    
}
