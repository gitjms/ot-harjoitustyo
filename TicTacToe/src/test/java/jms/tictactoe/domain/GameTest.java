package jms.tictactoe.domain;

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
public class GameTest {
    
    public Game instance;
    public Game instance2;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.instance = new Game(1);
        this.instance2 = new Game();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of top constructor, of class Game.
     */
    @Test
    public void testTopConstructor() {
        System.out.println("Testing: top constructor");
        this.instance2.setId(5);
        int result = this.instance2.getId();
        assertEquals(5, result);
    }

    /**
     * Test of setId method, of class Game.
     */
    @Test
    public void testSetId() {
        System.out.println("Testing: set and get Id");
        this.instance.setId(5);
        int result = this.instance.getId();
        assertEquals(5, result);
    }

    /**
     * Test of setWinner and getWinner method, of class Game.
     */
    @Test(expected=NullPointerException.class)
    public void testSetAndGetNullWinner() {
        System.out.println("Testing: set and get null winner");
        this.instance.setWinner(null);
        String result = this.instance.getWinner();
        fail(String.valueOf(result));
    }

    /**
     * Test of setWinner and getWinner method, of class Game.
     */
    @Test
    public void testSetAndGetWinner() {
        System.out.println("Testing: set and get winner");
        this.instance.setWinner("X");
        String expResult = "X";
        String result = this.instance.getWinner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test(expected=NullPointerException.class)
    public void testGetNullScore() {
        System.out.println("Testing: get null score");
        String player = "";
        int result = this.instance.getScore(player);
        fail(String.valueOf(result));
    }

    /**
     * Test of getScore method, of class Game.
     */
    @Test
    public void testGetScore() {
        System.out.println("Testing: get score");
        this.instance.setWinner("X");
        this.instance.setWinner("X");
        this.instance.setWinner("X");
        int result = this.instance.getScore("X");
        assertEquals(3, result);
    }

//    /**
//     * Test of equals method, of class Game.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("Testing: equals");
//        Object obj = null;
//        boolean expResult = false;
//        boolean result = this.instance.equals(obj);
//        assertEquals(expResult, result);
//    }
    
}
