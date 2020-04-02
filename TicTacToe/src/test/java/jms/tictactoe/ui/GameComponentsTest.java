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
        this.instance = new GameComponents();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getChoiceBox method, of class GameComponents.
     */
    @Test
    public void testGetChoiceBoxReturnsHBox() {
        System.out.println("GameComponents TEST: getChoiceBox returns HBox");
        Button Button1 = new Button();
        Button Button2 = new Button();
        Button Button3 = new Button();
        HBox result = this.instance.getChoiceBox(Button1, Button2, Button3);
        assertNotNull(result);
    }

    /**
     * Test of getChoiceBox method, of class GameComponents.
     */
    @Test
    public void testGetBottomBoxReturnsVBox() {
        System.out.println("GameComponents TEST: getBottomBox returns VBox");
        HBox  hBox1 = new HBox();
        HBox  hBox2 = new HBox();
        VBox result = this.instance.getBottomBox(hBox1, hBox2);
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testCreateButtonReturnsButton() {
        System.out.println("GameComponents TEST: createButton returns Button");
        Button result = this.instance.createButton("text");
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testCreateChoiceButtonReturnsButton() {
        System.out.println("GameComponents TEST: createChoiceButton returns Button");
        Button result = this.instance.createChoiceButton("text");
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAreaReturnsVBox() throws Exception {
        System.out.println("GameComponents TEST: getArea returns VBox");
        VBox result = this.instance.getArea(0, this.scoreService, this.fileScoreDao);
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testGetMainpaneReturnsBorderPane() {
        System.out.println("GameComponents TEST: getMainpane returns BorderPane");
        BorderPane result = this.instance.getMainpane();
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxBig method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxBigReturnsHBox() {
        System.out.println("GameComponents TEST: getScoreBoxBig returns HBox");
        HBox result = this.instance.getScoreBoxBig(this.scoreService);
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxSmall method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxSmallReturnsVBox() {
        System.out.println("GameComponents TEST: getScoreBoxSmall returns VBox");
        String text = "";
        VBox result = this.instance.getScoreBoxSmall(text);
        assertNotNull(result);
    }

    /**
     * Test of getButtonBox method, of class GameComponents.
     */
    @Test
    public void testGetButtonBoxReturnsHBox() {
        System.out.println("GameComponents TEST: getButtonBox returns HBox");
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
        System.out.println("GameComponents TEST: getButtonBox with null children returns fail");
        HBox result = this.instance.getButtonBox(null, null, null);
        fail(String.valueOf(result));
    }

    /**
     * Test of getLabel method, of class GameComponents.
     */
    @Test
    public void testGetLabelReturnsLabel() {
        System.out.println("GameComponents TEST: getLabel returns Label");
        Label result = this.instance.getLabel("", null, null, 0);
        assertNotNull(result);
    }

    /**
     * Test of getBloomEffect method, of class GameComponents.
     */
    @Test
    public void testGetBloomEffectReturnsBloom() {
        System.out.println("GameComponents TEST: getBloomEffect returns Bloom");
        Bloom result = this.instance.getBloomEffect();
        assertNotNull(result);
    }
    
}
