package jms.tictactoe.domain;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import jms.tictactoe.ui.TicTacToeUI;

/**
 *
 * @author jaris
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({jms.tictactoe.domain.GameTest.class})
public class DomainSuite {
    
    @BeforeClass
    public static void start() throws IOException, Exception {
        new TicTacToeUI().start();
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
