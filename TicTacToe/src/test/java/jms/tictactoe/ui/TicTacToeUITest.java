package jms.tictactoe.ui;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.stage.Stage;

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
public class TicTacToeUITest {
    
    private TicTacToeUI instance;
    
    public TicTacToeUITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, Exception {
        this.instance = new TicTacToeUI();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createButton method, of class TicTacToeUI.
     */
    @Test
    public void testCreateButtonReturnsButton() {
        System.out.println("TicTacToeUI TEST SUCCESS: createButton returns Button");
        String text = "";
        Stage stage = null;
        Button result = this.instance.createButton(text, stage);
        assertNotNull(result);
    }

}
