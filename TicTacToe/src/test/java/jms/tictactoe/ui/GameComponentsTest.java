package jms.tictactoe.ui;

import jms.tictactoe.domain.ScoreData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jms.tictactoe.dao.ScoreDataDao;
import jms.tictactoe.domain.ScoreService;
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
public class GameComponentsTest {
    
    private ScoreDataDao databaseDao;
    private Connection connection;
    private Statement statement;
    private ScoreService scoreService;
    private GameComponents instance;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("\nTESTING GAMECOMPONENTS CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        System.out.print("    - ");
        String testScoreData = "jdbc:h2:~/test";
        final String dbUser = "sa"; 
        final String dbPass = "";
        this.connection = DriverManager.getConnection(testScoreData, dbUser, dbPass);
        this.statement = this.connection.createStatement();
        ScoreData getData = new ScoreData(this.connection, this.statement);
        this.databaseDao = new ScoreDataDao(getData, this.connection, this.statement);
        this.scoreService = new ScoreService(this.databaseDao);
        this.instance = new GameComponents();
    }
    
    @After
    public void tearDown() throws SQLException {
        try {
            if(!this.statement.isClosed()) {
                this.statement.close();
            } 
        } catch(SQLException se) {
        }
        try {
            if(!this.connection.isClosed()) {
                this.connection.close();
            } 
        } catch(SQLException se) {
        }
    }

    /**
     * Test of getChoiceBox method, of class GameComponents.
     */
    @Test
    public void testGetChoiceBoxReturnsHBox() {
        System.out.println("testGetChoiceBoxReturnsHBox");
        Button Button1 = new Button();
        Button Button2 = new Button();
        Button Button3 = new Button();
        HBox box = new HBox();
        HBox result = this.instance.getChoiceBox(box, Button1, Button2, Button3);
        assertNotNull(result);
    }

    /**
     * Test of getChoiceBox method, of class GameComponents.
     */
    @Test
    public void testGetBottomBoxReturnsVBox() {
        System.out.println("testGetBottomBoxReturnsVBox");
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
        System.out.println("testCreateButtonReturnsButton");
        Button result = this.instance.createButton("text", 20, 20, 20, (new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY))));
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testCreateChoiceButtonReturnsButton() {
        System.out.println("testCreateChoiceButtonReturnsButton");
        Button result = this.instance.createButton("text", 20, 20, 20, (new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY))));
        assertNotNull(result);
    }

    /**
     * Test of getMainpane method, of class GameComponents.
     */
    @Test
    public void testGetMainpaneReturnsBorderPane() {
        System.out.println("testGetMainpaneReturnsBorderPane");
        BorderPane result = this.instance.getMainpane();
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxBig method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxBigReturnsHBox() {
        System.out.println("testGetScoreBoxBigReturnsHBox");
        HBox result = this.instance.getScoreBoxBig(this.scoreService);
        assertNotNull(result);
    }

    /**
     * Test of getScoreBoxSmall method, of class GameComponents.
     */
    @Test
    public void testGetScoreBoxSmallReturnsVBox() {
        System.out.println("testGetScoreBoxSmallReturnsVBox");
        String text = "";
        VBox result = this.instance.getScoreBoxSmall(text);
        assertNotNull(result);
    }

    /**
     * Test of getButtonBox method, of class GameComponents.
     */
    @Test
    public void testGetButtonBoxReturnsHBox() {
        System.out.println("testGetButtonBoxReturnsHBox");
        Button ngButton = new Button();
        Button rButton = new Button();
        Button qButton = new Button();
        HBox result = this.instance.getButtonBox(ngButton, rButton, qButton);
        assertNotNull(result);
    }

    /**
     * Test of getChoiceBox method, of class GameComponents.
     */
    @Test
    public void testGetDatabaseChoiceReturnsHBox() {
        System.out.println("testDatabaseChoiceReturnsHBox");
        Button button = new Button();
        TextField textField = new TextField();
        HBox result = this.instance.getDatabaseChoice(button, textField);
        assertNotNull(result);
    }

    /**
     * Test of getButtonBox method, of class GameComponents.
     */
    @Test(expected=NullPointerException.class)
    public void testGetButtonBoxWithNullChildrenReturnsFail() {
        System.out.println("testGetButtonBoxWithNullChildrenReturnsFail");
        HBox result = this.instance.getButtonBox(null, null, null);
        fail(String.valueOf(result));
    }
    
    /**
     * Test of getLabel method, of class GameComponents.
     */
    @Test
    public void testGetLabelReturnsLabel() {
        System.out.println("testGetLabelReturnsLabel");
        Label result = this.instance.getLabel("", null, null, 0, false);
        assertNotNull(result);
    }
    
}
