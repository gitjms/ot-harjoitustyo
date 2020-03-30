package jms.tictactoe.ui;

import de.saxsys.javafx.test.JfxRunner;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.ScoreService;
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
public class GameComponentsTest {
    
    private GameComponents instance;
    private FileScoreDao fileScoreDao;
    private ScoreService scoreService;
    
    public GameComponentsTest() {
    }
    
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
        this.instance = new GameComponents(this.scoreService,this.fileScoreDao);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testGetMainpaneReturnsBorderPane() {
        System.out.println("GameComponents TEST SUCCESS: getMainpane returns BorderPane");
        BorderPane result = this.instance.getMainpane();
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxBig method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxBigReturnsHBox() {
        System.out.println("GameComponents TEST SUCCESS: getScoreBoxBig returns HBox");
        HBox result = this.instance.getScoreBoxBig();
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxSmall method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxSmallReturnsVBox() {
        System.out.println("GameComponents TEST SUCCESS: getScoreBoxSmall returns VBox");
        String text = "";
        VBox result = this.instance.getScoreBoxSmall(text);
        assertNotNull(result);
    }

    /**
     * Test of getButtonBox method, of class GameComponents.
     */
    @Test
    public void testGetButtonBoxReturnsHBox() {
        System.out.println("GameComponents TEST SUCCESS: getButtonBox returns HBox");
        Button ngButton = new Button();
        Button rButton = new Button();
        Button qButton = new Button();
        HBox result = this.instance.getButtonBox(ngButton, rButton, qButton);
        assertNotNull(result);
    }

    /**
     * Test of getButtonBox method, of class GameComponents.
     */
    @Test(expected=NullPointerException.class)
    public void testGetButtonBoxWithNullChildrenReturnsFail() {
        System.out.println("GameComponents TEST SUCCESS: getButtonBox with null children returns fail");
        HBox result = this.instance.getButtonBox(null, null, null);
        fail(String.valueOf(result));
    }

    /**
     * Test of getLabel method, of class GameComponents.
     */
    @Test
    public void testGetLabelReturnsLabel() {
        System.out.println("GameComponents TEST SUCCESS: getLabel returns Label");
        Label result = this.instance.getLabel("", null, null, 0);
        assertNotNull(result);
    }

    /**
     * Test of getBloomEffect method, of class GameComponents.
     */
    @Test
    public void testGetBloomEffectReturnsBloom() {
        System.out.println("GameComponents TEST SUCCESS: getBloomEffect returns Bloom");
        Bloom result = this.instance.getBloomEffect();
        assertNotNull(result);
    }
    
}
