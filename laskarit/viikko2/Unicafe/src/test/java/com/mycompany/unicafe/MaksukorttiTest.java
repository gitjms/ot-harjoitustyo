package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        String vastaus = kortti.toString();
        assertEquals("saldo: 0.10", vastaus);
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 25.10", kortti.toString());
    }

    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.lataaRahaa(2500);
        // nyt kortin saldo on 10.0
        kortti.otaRahaa(1510);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void saldoEiMuutiJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(1510);
        // nyt kortin saldo on 10.0
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
        kortti.lataaRahaa(2500);
        // nyt kortin saldo on 25.10
        boolean vastaus = kortti.otaRahaa(1510);
        // nyt kortin saldo on 10.0, palauttaa true
        assertEquals(true, vastaus);
    }

    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiittaneet() {
        boolean vastaus = kortti.otaRahaa(1510);
        // nyt kortin saldo on 0.10, palauttaa false
        assertEquals(false, vastaus);
    }

    @Test
    public void metodiSaldoPalauttaaOikein() {
        int vastaus = kortti.saldo();
        assertEquals(10, vastaus);
    }
}
