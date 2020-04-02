package jms.tictactoe.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javafx.util.Pair;

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
public class ScoreServiceTest {
    
    private FileScoreDao fileScoreDao;
    private ScoreService instance;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        this.instance = new ScoreService(this.fileScoreDao);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createScore method, of class ScoreService.
     */
    @Test
    public void testCreateScoreReturnsTrue() {
        System.out.println("ScoreService TEST: createScore returns true");
        String id = "X";
        int points = 10;
        int games = 10;
        boolean result = this.instance.createScore(id, points, games);
        assertTrue(result);
    }

    /**
     * Test of createScore method, of class ScoreService.
     */
    @Test
    public void testCreateScoreReturnsFalse() {
        System.out.println("ScoreService TEST: createScore returns false");
        this.instance = new ScoreService(null);
        String id = "X";
        int points = 10;
        int games = 10;
        boolean result = this.instance.createScore(id, points, games);
        assertFalse(result);
    }

    /**
     * Test of getScore method, of class ScoreService.
     */
    @Test
    public void testGetScoreReturnsScore() {
        System.out.println("ScoreService TEST: getScore returns Score");
        Score result = this.instance.getScore();
        assertNotNull(result);
    }

    /**
     * Test of getScore and setScore methods, of class ScoreService.
     */
    @Test
    public void testSetAndGetScoreReturnsScore() {
        System.out.println("ScoreService TEST: get and set Score returns Score");
        Score score = new Score("X", 10, 10);
        this.instance.setScore(score);
        Score result = this.instance.getScore();
        assertEquals(result, score);
    }

    /**
     * Test of setPoints and getPoints methods, of class ScoreService.
     */
    @Test
    public void testSetAndGetPointsReturnsPoints() {
        System.out.println("ScoreService TEST: set and get Points returns Points");
        String id = "X";
        this.instance.setPoints(id,10);
        int result = this.instance.getPoints(id);
        assertEquals(10, result);
    }

    /**
     * Test of isScore method, of class ScoreService.
     */
    @Test
    public void testIsScoreReturnsTrue() {
        System.out.println("ScoreService TEST: isScore returns true");
        boolean result = this.instance.isScore();
        assertTrue(result);
    }

    /**
     * Test of isScore method, of class ScoreService.
     */
    @Test
    public void testIsScoreReturnsFalse() {
        System.out.println("ScoreService TEST: isScore returns false");
        this.instance = new ScoreService(null);
        boolean result = this.instance.isScore();
        assertEquals(false, result);
    }

    /**
     * Test of create method, of class ScoreService.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateReturnsScore() throws Exception {
        System.out.println("ScoreService TEST: create returns Score");
        Score expResult = new Score("X", 10, 10);
        Score result = this.instance.create(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllMap method, of class ScoreService.
     */
    @Test
    public void testSetAndGetAllMapReturnsMap() {
        System.out.println("ScoreService TEST: set and get Map returns Map");
        Map<String, Pair<Integer, Integer>> expResult = new HashMap();
        this.instance.setAllMap(expResult);
        Map<String, Pair<Integer, Integer>> result = this.instance.getAllMap();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetPoints method, of class ScoreService.
     */
    @Test
    public void testResetPointsReturnsZeroPoints() {
        System.out.println("ScoreService TEST: resetPoints returns zero Points");
        String id = "X";
        this.instance.setPoints(id, 10);
        int resultBefore = this.instance.getPoints(id);
        this.instance.resetPoints();
        int resultAfter = this.instance.getPoints(id);
        assertEquals(10, resultBefore);
        assertEquals(0, resultAfter);
    }

    /**
     * Test of setGames and getGames methods, of class ScoreService.
     */
    @Test
    public void testSetAndGetGamesReturnsGames() {
        System.out.println("Score TEST: set and get Games returns Games");
        this.instance.resetPoints();
        this.instance.setGames(10);
        int result = this.instance.getGames();
        assertEquals(10, result);
    }

    /**
     * Test of setPoints and getPoints methods, of class ScoreService.
     */
    @Test
    public void testSetPointsAndGetGamesReturnsZero() {
        System.out.println("ScoreService TEST: set Games and get Games returns zero");
        this.instance = new ScoreService(null);
        int result = this.instance.getGames();
        assertEquals(0, result);
    }

    /**
     * Test of setPoints and getPoints methods, of class ScoreService.
     */
    @Test
    public void testSetPointsAndGetPointsReturnsPoints() {
        System.out.println("ScoreService TEST: set Points and get Points returns Points");
        String id = "X";
        this.instance.setPoints(id, 10);
        int result = this.instance.getPoints(id);
        assertEquals(10, result);
    }

    /**
     * Test of setPoints and getPoints methods, of class ScoreService.
     */
    @Test
    public void testSetPointsAndGetPointsReturnsZero() {
        System.out.println("ScoreService TEST: set Points and get Points returns zero");
        this.instance = new ScoreService(null);
        String id = "X";
        int result = this.instance.getPoints(id);
        assertEquals(0, result);
    }
    
}
