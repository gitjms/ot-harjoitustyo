package jms.tictactoe.domain;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import de.saxsys.javafx.test.JfxRunner;

/**
 *
 * @author jaris
 */
@RunWith(JfxRunner.class)
public class ScoreTest {
    
    private Score instance;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException, Exception {
        this.instance = new Score("", 0, 0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setId and getId methods, of class Score.
     */
    @Test
    public void testSetAndGetIdReturnsId() {
        System.out.println("Score TEST: setId and getId returns Id");
        String id = "Donald Duck";
        this.instance.setId(id);
        String result = this.instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setPoints and getPoints methods, of class Score.
     */
    @Test
    public void testAddOnePointAndGetPointsReturnsPoints() {
        System.out.println("Score TEST: add one Point and get Points returns Points");
        String id = "Donald Duck";
        this.instance.setPoints();
        int result = this.instance.getPoints(id);
        assertEquals(1, result);
    }

    /**
     * Test of setPoints and getPoints methods, of class Score.
     */
    @Test
    public void testAddPointsAndGetPointsReturnsPoints() {
        System.out.println("Score TEST: add Points and get Points returns Points");
        String id = "Donald Duck";
        this.instance.setPoints(id,10);
        int result = this.instance.getPoints(id);
        assertEquals(10, result);
    }

    /**
     * Test of setGames and getGames methods, of class Score.
     */
    @Test
    public void testAddOneGameAndGetGamesReturnsGames() {
        System.out.println("Score TEST: add one Game and get Games returns Games");
        int resultBefore = this.instance.getGames();
        this.instance.setGames();
        int resultAfter = this.instance.getGames();
        assertEquals(1, resultAfter-resultBefore);
    }

    /**
     * Test of setGames and getGames methods, of class Score.
     */
    @Test
    public void testAddGamesAndGetGamesReturnsGames() {
        System.out.println("Score TEST: add Games and get Games returns Games");
        int resultBefore = this.instance.getGames();
        this.instance.setGames(10);
        int resultAfter = this.instance.getGames();
        assertEquals(10, resultAfter-resultBefore);
    }
    
}
