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
public class WinRowTest {
    
    public WinRowTest() {
    }
    
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
        System.out.println("WinRow TEST SUCCESS: values returns values");
        WinRow[] expResult = {WinRow.X3,WinRow.O3,WinRow.X4,WinRow.O4,WinRow.X5,WinRow.O5};
        WinRow[] result = WinRow.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class WinRow.
     */
    @Test
    public void testValueOfReturnsValueOf() {
        System.out.println("WinRow TEST SUCCESS: valueOf returns valueOf");
        String result = WinRow.valueOf("X3").name();
        assertEquals(WinRow.X3.name(), result);
    }

    /**
     * Test of getWinCode method, of class WinRow.
     */
    @Test
    public void testGetWinCode() {
        System.out.println("getWinCode");
        String result = WinRow.X3.getWinCode();
        assertEquals("XXX", result);
    }
    
}
