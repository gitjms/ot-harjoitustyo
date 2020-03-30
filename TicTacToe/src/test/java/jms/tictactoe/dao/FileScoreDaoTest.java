package jms.tictactoe.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.util.Pair;
import jms.tictactoe.domain.Score;
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
public class FileScoreDaoTest {
    
    private FileScoreDao instance;
    private String scoreFile;
    
    public FileScoreDaoTest() {
    }
    
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
        this.scoreFile = properties.getProperty("scoreFile");
        this.instance = new FileScoreDao(this.scoreFile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class FileScoreDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateWithEmptyFileFailsNot() throws Exception {
        System.out.println("FileScoreDao TEST SUCCESS: create with empty file fails not");
        try (FileWriter writer = new FileWriter(new File(this.scoreFile))) {
            writer.write("");
        }
        try {
            this.instance = new FileScoreDao(this.scoreFile);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of create method, of class FileScoreDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateWithNoFileFailsNot() throws Exception {
        System.out.println("FileScoreDao TEST SUCCESS: create with no file fails not");
        try {
            this.instance = new FileScoreDao("missingFile.txt");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of create method, of class FileScoreDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateReturnsScore() throws Exception {
        System.out.println("FileScoreDao TEST SUCCESS: create returns Score");
        Score expResult = new Score("X", 10, 10);
        Score result = this.instance.create(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class FileScoreDao.
     */
    @Test
    public void testGetScoreReturnsScore() {
        System.out.println("FileScoreDao TEST SUCCESS: getScore returns Score");
        Score result = this.instance.getScore();
        assertNotNull(result);
    }

    /**
     * Test of isScore method, of class FileScoreDao.
     */
    @Test
    public void testIsScoreReturnsTrue() {
        System.out.println("FileScoreDao TEST SUCCESS: isScore returns true");
        boolean result = this.instance.isScore();
        assertTrue(result);
    }

    /**
     * Test of isScore method, of class FileScoreDao.
     * @throws java.lang.Exception
     */
    @Test(expected=NullPointerException.class)
    public void testIsScoreReturnsFalse() throws Exception {
        System.out.println("ScoreService TEST SUCCESS: isScore returns false");
        this.instance = new FileScoreDao(null);
        boolean result = this.instance.isScore();
        fail(String.valueOf(result));
    }

    /**
     * Test of getAllMap method, of class FileScoreDao.
     */
    @Test
    public void testSetAndGetAllMapReturnsMap() {
        System.out.println("FileScoreDao TEST SUCCESS: set and get Map returns Map");
        Map<String, Pair<Integer, Integer>> expResult = new HashMap();
        this.instance.setAllMap(expResult);
        Map<String, Pair<Integer, Integer>> result = this.instance.getAllMap();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetPoints method, of class FileScoreDao.
     */
    @Test
    public void testResetPointsReturnsZeroPoints() {
        System.out.println("FileScoreDao TEST SUCCESS: resetPoints returns zero Points");
        String id = "X";
        this.instance.setPoints(id,10);
        int resultBefore = this.instance.getPoints(id);
        this.instance.resetPoints();
        int resultAfter = this.instance.getPoints(id);
        assertEquals(10, resultBefore);
        assertEquals(0, resultAfter);
    }

    /**
     * Test of setPoints method, of class FileScoreDao.
     */
    @Test
    public void testSetPointsAndGetPointsReturnsPoints() {
        System.out.println("FileScoreDao TEST SUCCESS: set Points and get Points returns Points");
        String id = "X";
        this.instance.setPoints(id,10);
        int result = this.instance.getPoints(id);
        assertEquals(10, result);
    }

    /**
     * Test of getGames method, of class FileScoreDao.
     */
    @Test
    public void testSetAndGetGamesReturnsGames() {
        System.out.println("FileScoreDao TEST SUCCESS: set and get Games returns Games");
        this.instance.resetPoints();
        this.instance.setGames(10);
        int result = this.instance.getGames();
        assertEquals(10, result);
    }

    /**
     * Test of getGames method, of class FileScoreDao.
     */
    @Test
    public void testGetGamesWithNoScoreReturnsZero() {
        System.out.println("FileScoreDao TEST SUCCESS: set and get Games returns Games");
        this.instance.setScore(null);
        int result = this.instance.getGames();
        assertEquals(0, result);
    }

    /**
     * Test of getGames method, of class FileScoreDao.
     */
    @Test
    public void testSetAndGetGamesWithNoScoreReturnsZero() {
        System.out.println("FileScoreDao TEST SUCCESS: set and get Games returns Games");
        this.instance.setScore(null);
        this.instance.setGames(10);
        int result = this.instance.getGames();
        assertEquals(0, result);
    }
    
}
