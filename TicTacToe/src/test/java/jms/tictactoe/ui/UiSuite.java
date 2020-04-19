package jms.tictactoe.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jaris
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    jms.tictactoe.ui.GameAreaTest.class,
    jms.tictactoe.ui.GameComponentsTest.class,
    jms.tictactoe.ui.GameSizeTest.class,
    jms.tictactoe.ui.GameSquareTest.class,
    jms.tictactoe.ui.ScoreDataTest.class,
    jms.tictactoe.ui.WinRowTest.class
})
public class UiSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
