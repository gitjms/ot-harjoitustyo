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
        System.out.println("\nTESTING WINROW CLASS");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.print("    - ");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class WinRow.
     */
    @Test
    public void testValuesReturnsValues() {
        System.out.println("testValuesReturnsValues");
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
        System.out.println("testValueOfWinRowReturnsValueOfWinRow");
        WinRow.X.setWinCode("XXX");
        String result = WinRow.valueOf("X").name();
        assertEquals(WinRow.X.name(), result);
    }

    /**
     * Test of getWinCode method, of class WinRow.
     */
    @Test
    public void testSetAndGetWinCodeReturnsWinCode() {
        System.out.println("testSetAndGetWinCodeReturnsWinCode");
        WinRow.X.setWinCode("XXX");
        String result = WinRow.X.getWinCode();
        assertEquals("XXX", result);
    }
    
}
