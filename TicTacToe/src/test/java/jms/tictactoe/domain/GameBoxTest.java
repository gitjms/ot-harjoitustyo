package jms.tictactoe.domain;

import de.saxsys.javafx.test.JfxRunner;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import jms.tictactoe.dao.FileScoreDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author jaris
 */
@RunWith(JfxRunner.class)
public class GameBoxTest {
    
    public Gamebox instance;
    private ScoreService scoreService;
    private FileScoreDao fileScoreDao;
    public Game game;
    public Label label1;
    public Label label2;
    
    public GameBoxTest() {
        super();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        this.label1 = new Label();
        this.label2 = new Label();
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        this.scoreService = new ScoreService(this.fileScoreDao);
        this.game = new Game(1,this.scoreService);
        this.instance = new Gamebox(this.game,this.scoreService,this.fileScoreDao);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createBox method, of class Gamebox.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateBoxReturnsVBox() throws Exception {
        System.out.println("GameSquare TEST SUCCESS: createBox returns VBox");
        VBox result = this.instance.createBox(this.label1,this.label2,this.scoreService,this.fileScoreDao);
        assertNotNull(result);
    }

    /**
     * Test of createBox method, of class Gamebox.
     * @throws java.lang.Exception
     */
    @Test(expected=NullPointerException.class)
    public void testCreateBoxWithNullChildrenReturnsFail() throws Exception {
        System.out.println("GameSquare TEST SUCCESS: createBox with null children returns fail");
        VBox result = this.instance.createBox(null,null,null,null);
        fail(String.valueOf(result));
    }

    /**
     * Test of setGridpane method, of class Gamebox.
     */
    @Test
    public void testSetAndGetGridpaneReturnsGridPane() {
        System.out.println("GameSquare TEST SUCCESS: set and get GridPane returns GridPane");
        this.instance.setGridpane();
        GridPane result = this.instance.getGridpane();
        assertNotNull(result);
    }
    
}
