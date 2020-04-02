package jms.tictactoe.ui;

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
public class WinRowTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class WinRow.
     */
    @Test
    public void testValuesReturnsValues() {
        System.out.println("WinRow TEST: values returns values");
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOOOO");
        WinRow[] expResult = {WinRow.X, WinRow.O};
        WinRow[] result = WinRow.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class WinRow.
     */
    @Test
    public void testValueOfWinRowReturnsValueOfWinRow() {
        System.out.println("WinRow TEST: valueOf WinRow returns valueOf WinRow");
        WinRow.X.setWinCode("XXX");
        String result = WinRow.valueOf("X").name();
        assertEquals(WinRow.X.name(), result);
    }

    /**
     * Test of getWinCode method, of class WinRow.
     */
    @Test
    public void testSetAndGetWinCodeReturnsWinCode() {
        System.out.println("WinRow TEST: set and get WinCode returns WinCode");
        WinRow.X.setWinCode("XXX");
        String result = WinRow.X.getWinCode();
        assertEquals("XXX", result);
    }
    
}
